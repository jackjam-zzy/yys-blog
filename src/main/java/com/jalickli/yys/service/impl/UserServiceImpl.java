package com.jalickli.yys.service.impl;

import com.jalickli.yys.entity.User;
import com.jalickli.yys.mapper.UserMapper;
import com.jalickli.yys.service.UserService;
import com.jalickli.yys.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
