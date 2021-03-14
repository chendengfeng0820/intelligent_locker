package com.deepglint.register_login.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.deepglint.api.util.RedisUtil;
import com.deepglint.register_login.config.SendSmsConfig;
import com.deepglint.api.pojo.*;
import com.deepglint.register_login.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @ClassName RegisterController
 * @Description 注册
 * @author: cdf
 * @Date: 2021-02-16 09:39
 **/
@RestController
@Slf4j
public class RegisterController {

    @Autowired
    private SendSmsConfig sendSmsConfig;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserRegisterService userRegisterService;

    /**
     * 发送短信验证码
     * @param telephone 手机号
     * @return
     */
    @RequestMapping(value = "/sendcode",method = RequestMethod.POST)
    public String sendCode(@RequestParam String telephone) {
        String s = sendSmsConfig.sendSms(telephone);
        //解析json  获取阿里云接口发送成功标志进行下一步操作
        JSONObject jsonObject1 = JSON.parseObject(s);
        //获取接口data状态信息
        JSONObject jsonObject2 = JSONObject.parseObject(jsonObject1.get("data").toString());
        String message = jsonObject2.get("Message").toString();
        log.info("Message : {}", message);
        if (("OK").equals(message)) {
            log.info("发送成功：" + telephone);
            return JSON.toJSONString("已发送");
        } else {
            return JSON.toJSONString("对不起，发送失败，请稍候重试");
        }
    }

    /**
     * 登录注册信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody User user,@RequestParam("code") String code) {
        int number = 0 ;
        String verificationCode = (String) redisUtil.getHash(user.getTelephone(),"code");
        // redis 中 hash 取出来的总是多一组 "" , 去掉 .
        verificationCode = verificationCode.replace("\"","");
        if (verificationCode.equals(code)){
            try {
                number = userRegisterService.userRegister(user);
            }catch (Exception e){
                if (e.getCause() instanceof SQLIntegrityConstraintViolationException){
                    log.error("手机号已经注册 {}",user.getTelephone());
                    return "手机号已注册,请直接登录";
                }
            }
            log.info("用户: {} ,注册成功",user.getTelephone());
        } else {
            return "验证码错误,请重试";
        }
        return String.valueOf(number);
    }



//            /**
//             * 反射方法调用
//             */
//        try {
//            Class<?> aClass = Class.forName("com.info.register_login.config.SendSmsConfig");
//            Method method = aClass.getMethod("sendSms", String.class);
//            Object instance = aClass.newInstance();
//            method.invoke(instance, user.getUser_telephone())
//        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
}
