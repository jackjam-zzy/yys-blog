package com.jalickli.yys.service;

import com.jalickli.yys.entity.User;

public interface UserService {
    User checkUser(String username, String password);
}
