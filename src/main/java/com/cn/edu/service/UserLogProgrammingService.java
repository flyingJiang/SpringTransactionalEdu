package com.cn.edu.service;

import com.cn.edu.dao.UserLogDAO;
import com.cn.edu.domain.UserLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: TransactionalEdu
 * @description: 编程式事务
 * @author: jiangjianfei
 * @create: 2023-04-27 15:00
 **/
@Slf4j
@Service
public class UserLogProgrammingService {
    @Autowired
    private UserLogDAO userLogDAO;

    public void saveLog(String username) {
        UserLog userLog = new UserLog();
        userLog.setUsername(username);
        userLog.setDescription(String.valueOf(new Date()));
        userLogDAO.save(userLog);
    }
}
