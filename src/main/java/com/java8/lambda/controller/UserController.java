package com.java8.lambda.controller;

import com.java8.lambda.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     *  MyBatisPlus 查询数据库
     * @return
     */
    @RequestMapping("queryList")
    public String selectList(){
        return userService.selectList();
    }


    /**
     *  MyBatisPlus 查询数据库
     * @return
     */
    @RequestMapping("queryListLambda")
    public String selectListLambda(){
        return userService.queryListLambda();
    }
}
