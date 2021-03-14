package com.deepglint.register_login.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deepglint.api.pojo.User;
import com.deepglint.api.util.RedisUtil;
import com.deepglint.register_login.service.UserRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LoginController
 * @Description TODO
 * @author: cdf
 **/
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserRegisterService userRegisterService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@RequestParam("telephone") String telephone, @RequestParam("password") String password) {
    public String login(@RequestBody User user) {
        String telephone = user.getTelephone();
        String password  = user.getPassword();
        String databasePassword = userRegisterService.login(telephone);
        if (password.equals(databasePassword)) {
            return "登录成功";
        } else {
            return "对不起，账号或密码错误";
        }
    }
}
