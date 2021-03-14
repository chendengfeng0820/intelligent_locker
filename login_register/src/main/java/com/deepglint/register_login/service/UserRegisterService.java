package com.deepglint.register_login.service;

import com.deepglint.api.pojo.*;

import java.util.List;


public interface UserRegisterService {

    public int userRegister(User user);

    public String login(String telephone);

//    public List<User> findAll();
}
