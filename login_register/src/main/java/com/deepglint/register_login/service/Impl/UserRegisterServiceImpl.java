package com.deepglint.register_login.service.Impl;

import com.deepglint.register_login.mapper.*;
import com.deepglint.api.pojo.*;
import com.deepglint.register_login.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserRegisterServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-16 13:03
 **/
@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    private UserRegisterMapper userRegisterMapper;

    @Override
    public int userRegister(User user) {
        userRegisterMapper.userRegister(user);
        int number = user.getUserId();
        return number;
    }

    @Override
    public String login(String telephone) {
        String password = userRegisterMapper.login(telephone);
        return password;
    }

//    @Override
//    public List<User> findAll() {
//        List<User> select = userRegisterMapper.findAll();
//        return select;
//    }
}
