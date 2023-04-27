package com.cn.edu.controller;

import com.cn.edu.service.UserProgrammingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: TransactionalEdu
 * @description: 编程式事务
 * @author: jiangjianfei
 * @create: 2023-04-27 15:03
 **/
@RestController
@RequestMapping(value = {"/v1/user/program"})
public class UserProgrammingController {
    @Autowired
    private UserProgrammingService userProgrammingService;

    @GetMapping("/method1")
    @ResponseBody
    public Object method1(String username) {
        userProgrammingService.save(username);
        return "ok";
    }

    @GetMapping("/method2")
    @ResponseBody
    public Object method2(String username) {
        userProgrammingService.save2(username);
        return "ok";
    }
}
