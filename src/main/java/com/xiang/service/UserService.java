package com.xiang.service;

import com.xiang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void test() {
        System.out.println(userMapper);
    }
}