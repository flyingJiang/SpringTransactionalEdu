package com.cn.edu.service;


import com.cn.edu.dao.UserDAO;
import com.cn.edu.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: UserService
 * @description: 声明式事务
 * @author: jiangjianfei
 * @create: 2023-04-25 19:38
 **/
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserLogService userLogService;

    /**
     * 两个方法都是才有默认的事务传播属性Propagation.REQUIRED
     * <p>
     * Propagation.REQUIRED：如果当前存在事务，那么就加入这个事务，不存在就新建一个事务。
     * <p>
     * 结果：这里也是两个方法都没有操作成功，因为REQUIRE的事务传播机制下，
     * 调用者存在了一个事务，被调用者在已经存在事务的情况下，加入调用者这个事务，而不自己新创建，所以两者在同一事务种，被调用方法发生异常，整个事务回滚，都不操作成功
     * ————————————————
     * 版权声明：本文为CSDN博主「傻露的大天才」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/qq_45646289/article/details/124882146
     *
     * @param userName
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void updateUserName(String userName) {
        User user = new User();
        user.setId(1L);
        user.setUsername(userName);
//        userDAO.save(user);
        // 被调用异常，全部回滚
        // TODO: 2023/4/27  
        try {
            userLogService.saveLog(userName);
        } catch (Exception e) {
            log.error("e = ", e);
        }
        userDAO.save(user);
//        int i = 10 / 0;

    }

    /**
     * SUPPORTS
     * Propagation.SUPPORTS:如果当前有事务，加入事务，如果没有则不使用事务
     * 调用方法的传播属性是Propagation.REQUIRED，被调用的是Propagation.SUPPORTS
     * 结果：这里也是两个方法都没有将数据操作成功–用户名称没有修改，修改日志也没有插入，发生了异常，两个数据操作都回滚了。
     * 因为调用方法存在事务，而被调用方法可以支持事务，有则加入到这个事务中，所以两方法处于同一事务，有异常则两者都回滚。
     *
     * @param userName
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void updateUserName2(String userName) {
        User user = new User();
        user.setId(1L);
        user.setUsername(userName);
        userDAO.save(user);
        userLogService.saveLog2(userName);
    }

    /**
     * 调用方法的没有事务，被调用的是Propagation.SUPPORTS
     * 结果：这里也是两个方法都将数据操作成功–用户名称修改，修改日志也插入即使有异常，因为SUPPORTS没有处在事务中，所以被调用方法也没使用事务执行,即使有异常也没有回滚
     *
     * @param userName
     */
    public void updateUserName3(String userName) {
        User user = new User();
        user.setId(1L);
        user.setUsername(userName);
        userDAO.save(user);
        userLogService.saveLog2(userName);
    }

    /**
     * NOT_SUPPORTED:表示不支持事务，如果有事务也不加入事务，没有事务以非事务运行
     * 调用方法没有事务，被调用的是pagation = Propagation.NOT_SUPPORTED
     * 结果：调用方法没有事务，被调用方法不支持事务，即使报错，数据库都操作成功。
     *
     * @param userName
     */
    public void updateUserName4(String userName) {
        User user = new User();
        user.setId(1L);
        user.setUsername(userName);
        userDAO.save(user);
        userLogService.saveLog4(userName);
    }

    /**
     * 调用方法的传播属性是Propagation.REQUIRED，被调用的是pagation = Propagation.NOT_SUPPORTED,且调用方法发生异常，被调用方法也发生异常。
     * 结果：调用方法没有操作数据库成功，因为REQUIRED新建了一个新的事务，发生异常回滚。
     * 但是被调用方法操作数据库成功，因为NOT_SUPPOTS不支持事务，即使存在事务也不加入，所以即使有异常，也不回滚。
     *
     * @param userName
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void updateUserName5(String userName) {
        User user = new User();
        user.setId(1L);
        user.setUsername(userName);
        userDAO.save(user);
        userLogService.saveLog4(userName);
    }

    /**
     * REQUIRES_NEW:不管是否存在事务，都以最新的事务执行，执行完在执行旧的事务
     * 调用方法的传播属性是Propagation.REQUIRED，被调用方法的是Propagation.REQUIRES_NEW
     * 结果：调用方法操作数据库失败，因为自己所在得事务中有异常，且有事务，操作回滚。
     * 被调用操作数据库成功，因为创建了一个新的事务，与已经存在的事务无关，没有异常正常执行，调用方法和被调用方法处于两个不同得事务，因为.REQUIRES_NEW以新的事务执行。
     *
     * @param userName
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void updateUserName6(String userName) {
        User user = new User();
        user.setId(1L);
        user.setUsername(userName);
        userDAO.save(user);
        // 如果被调异常，希望对调用者没有影响，需要捕获异常
        userLogService.saveLog6(userName);
//        int i = 10 / 0;
    }

    /**
     * MANDATORY（强制的）：必须在一个事务中执行，如果没有事务，则抛出异常
     * 调用方法没有加入事务，被调用方法使用MANDATORY
     * 结果：调用方法正常执行数据库操作，被调用方法抛出异常，且没有执行数据库操作（异常中断了程序），抛出的异常时因为被调用法必须在事务中执行，没有事务，抛出异常，异常如下：
     * org.springframework.transaction.IllegalTransactionStateException: No existing transaction found for transaction marked with propagation 'mandatory'
     *
     * @param userName
     */
    public void updateUserName7(String userName) {
        User user = new User();
        user.setId(1L);
        user.setUsername(userName);
        userDAO.save(user);
        userLogService.saveLog7(userName);
    }

    /**
     * NEVER:以非事务的方式执行，如果存在事务异常
     * 调用方法使用的QEQUIRE,会创建一个新的事务，被调用方法使用NEVER，两个方法都没有额外的异常。
     * 结果：调用方法操作数据库不成功，被调用方法操作数据库不成功。
     * 因为被调用方法时NEVER的传播机制，调用方法有事务，被调用方法会抛出异常，导致本身执行不成功，抛出的异常也会导致调用方法的事务回滚，操作不成功。抛出的异常如下：
     *
     * @param userName
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void updateUserName8(String userName) {
        User user = new User();
        user.setId(1L);
        user.setUsername(userName);
        userDAO.save(user);
        userLogService.saveLog8(userName);
    }

    /*
    使用嵌套事务是有条件的：
    数据库的支持
    JDK 1.4 才支持 java.sql.Savepoint 。所以JDK必须在1.4 及以上
    还需要Spring中配置nestedTransactionAllowed=true。
     */

    /**
     * PROPAGATION_NESTED：如果调用者不存在事务，那么被调用者自己创建事务，这种情况和REQUIRE一样。
     *
     * @param userName
     */
    public void updateUserName9(String userName) {
        User user = new User();
        user.setId(1L);
        user.setUsername(userName);
        userDAO.save(user);
        userLogService.saveLog9(userName);
    }

    /**
     * 如果调用者存在事务，那么被调用者就在调用者的事务里嵌套一个事务，称为嵌套事务。
     * org.springframework.transaction.NestedTransactionNotSupportedException: JpaDialect does not support savepoints - check your JPA provider's capabilities
     * 结果就是没有结果，因为 JPA 是不支持这种循环嵌套事务的，所以在运行的时候就会抛出异常
     *
     * @param userName
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void updateUserName10(String userName) {
        User user = new User();
        user.setId(1L);
        user.setUsername(userName);
        userDAO.save(user);
        userLogService.saveLog9(userName);
    }

}
