package com.cn.edu.service;

import com.cn.edu.dao.UserDAO;
import com.cn.edu.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @program: TransactionalEdu
 * @description: 编程式事务
 * @author: jiangjianfei
 * @create: 2023-04-27 14:28
 **/
@Slf4j
@Service
public class UserProgrammingService {
    @Resource
    private TransactionTemplate transactionTemplate;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserLogProgrammingService userLogProgrammingService;

    public final void save(String username) {
        transactionTemplate.execute((status) -> {
            User user = new User();
            user.setId(1L);
            user.setUsername(username);
            userDAO.save(user);
            userLogProgrammingService.saveLog(username);
            return Boolean.TRUE;
        });
    }

    public final void save2(String username) {
        transactionTemplate.execute((status) -> {
            User user = new User();
            user.setId(1L);
            user.setUsername(username);
            userDAO.save(user);
            userLogProgrammingService.saveLog(username);
            int i = 10 / 0;
            return Boolean.TRUE;
        });
    }

}
