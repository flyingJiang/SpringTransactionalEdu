package com.cn.edu.service;

import com.cn.edu.dao.UserLogDAO;
import com.cn.edu.domain.UserLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @program: UserLogService
 * @description: 声明式事务
 * @author: jiangjianfei
 * @create: 2023-04-26 10:58
 **/

@Slf4j
@Service
public class UserLogService {
    @Autowired
    private UserLogDAO userLogDAO;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void saveLog(String username) {
        UserLog userLog = new UserLog();
        userLog.setUsername(username);
        userLog.setDescription(String.valueOf(new Date()));
        userLogDAO.save(userLog);
        int i = 10 / 0;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
    public void saveLog2(String username) {
        UserLog userLog = new UserLog();
        userLog.setUsername(username);
        userLog.setDescription(String.valueOf(new Date()));
        userLogDAO.save(userLog);
        int i = 10 / 0;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = ArithmeticException.class)
    public void saveLog4(String username) {
        UserLog userLog = new UserLog();
        userLog.setUsername(username);
        userLog.setDescription(String.valueOf(new Date()));
        userLogDAO.save(userLog);
        int i = 10 / 0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void saveLog6(String username) {
        UserLog userLog = new UserLog();
        userLog.setUsername(username);
        userLog.setDescription(String.valueOf(new Date()));
        userLogDAO.save(userLog);
        int i = 10 / 0;
    }

    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Throwable.class)
    public void saveLog7(String username) {
        UserLog userLog = new UserLog();
        userLog.setUsername(username);
        userLog.setDescription(String.valueOf(new Date()));
        userLogDAO.save(userLog);
    }

    @Transactional(propagation = Propagation.NEVER, rollbackFor = Throwable.class)
    public void saveLog8(String username) {
        UserLog userLog = new UserLog();
        userLog.setUsername(username);
        userLog.setDescription(String.valueOf(new Date()));
        userLogDAO.save(userLog);
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = Throwable.class)
    public void saveLog9(String username) {
        UserLog userLog = new UserLog();
        userLog.setUsername(username);
        userLog.setDescription(String.valueOf(new Date()));
        userLogDAO.save(userLog);
    }

}
