package com.cn.edu.controller;

import com.cn.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: TransactionalEdu
 * @description:
 * @author: jiangjianfei
 * @create: 2023-04-26 11:14
 **/
@RestController
@RequestMapping(value = {"/v1/user"})
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Propagation.REQUIRED+Propagation.REQUIRED
     *
     * @param username
     * @return
     */
    @GetMapping("/method1")
    @ResponseBody
    public Object method1(String username) {
        userService.updateUserName(username);
        return "ok";
    }

    /**
     * Propagation.REQUIRED+Propagation.SUPPORTS
     *
     * @param username
     * @return
     */
    @GetMapping("/method2")
    @ResponseBody
    public Object method2(String username) {
        userService.updateUserName2(username);
        return "ok";
    }

    /**
     * NO+Propagation.SUPPORTS
     *
     * @param username
     * @return
     */
    @GetMapping("/method3")
    @ResponseBody
    public Object method3(String username) {
        userService.updateUserName3(username);
        return "ok";
    }

    /**
     * NO+Propagation.NOT_SUPPORTED
     *
     * @param username
     * @return
     */
    @GetMapping("/method4")
    @ResponseBody
    public Object method4(String username) {
        userService.updateUserName4(username);
        return "ok";
    }

    /**
     * Propagation.REQUIRED+Propagation.NOT_SUPPORTED
     *
     * @param username
     * @return
     */
    @GetMapping("/method5")
    @ResponseBody
    public Object method5(String username) {
        userService.updateUserName5(username);
        return "ok";
    }

    /**
     * Propagation.REQUIRED+Propagation.REQUIRES_NEW
     *
     * @param username
     * @return
     */
    @GetMapping("/method6")
    @ResponseBody
    public Object method6(String username) {
        userService.updateUserName6(username);
        return "ok";
    }

    /**
     * NO+Propagation.MANDATORY
     *
     * @param username
     * @return
     */
    @GetMapping("/method7")
    @ResponseBody
    public Object method7(String username) {
        userService.updateUserName7(username);
        return "ok";
    }

    /**
     * Propagation.REQUIRED+Propagation.NEVER
     *
     * @param username
     * @return
     */
    @GetMapping("/method8")
    @ResponseBody
    public Object method8(String username) {
        userService.updateUserName8(username);
        return "ok";
    }

    /**
     * NO+Propagation.NESTED
     *
     * @param username
     * @return
     */
    @GetMapping("/method9")
    @ResponseBody
    public Object method9(String username) {
        userService.updateUserName9(username);
        return "ok";
    }

    /**
     * Propagation.REQUIRED+Propagation.NESTED
     *
     * @param username
     * @return
     */
    @GetMapping("/method10")
    @ResponseBody
    public Object method10(String username) {
        userService.updateUserName10(username);
        return "ok";
    }
}
