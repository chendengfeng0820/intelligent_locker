package com.deepglint.back_management.controller;

import com.alibaba.fastjson.JSON;
import com.deepglint.api.pojo.User;
import com.deepglint.back_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @author: cdf
 * @Date: 2021-03-06 16:48
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;


	@RequestMapping("/hello")
	public String syaHello(){
	    return "hello";
    }

	@PostMapping(value = "/insert")
	public String insertUser(@RequestBody User user){
		userService.insertUser(user);
		return "insert ok";
	}


	@DeleteMapping(value = "/delete/{userId}")
	public String deleteUser(@PathVariable("userId") int userId){
		userService.deleteUser(userId);
		return "delete ok";
	}


	@PutMapping(value = "/update")
	public String updateUser(@RequestBody User user){
		userService.updateUser(user);
		return "update ok";
	}


	@GetMapping(value = "/get/{userId}")
	public String selectUser(@PathVariable("userId") int userId){
		User user =  userService.selectUser(userId);
		return JSON.toJSONString(user);
	}

	@GetMapping(value = "/getAllUser")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}


}
