package com.java8.lambda.service.impl;

import com.alibaba.fastjson.JSON;
import com.java8.lambda.dao.UserMapper;
import com.java8.lambda.pojo.User;
import com.java8.lambda.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String selectList(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        return JSON.toJSONString(users);
    }

    @Override
    public String queryListLambda() {
        return null;
    }
}
