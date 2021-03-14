package com.deepglint.register_login.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Hello
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-15 22:37
 **/
@RestController
@Slf4j
public class Hello {

    @RequestMapping("/hello")
    public String sayHello(){
        log.info("say hello");
        return "hello";
    }





}
