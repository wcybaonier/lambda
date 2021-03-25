package com.java8.lambda.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java8.lambda.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询所有用户信息
     * @return list
     */
    List<User> selectAll();
}
