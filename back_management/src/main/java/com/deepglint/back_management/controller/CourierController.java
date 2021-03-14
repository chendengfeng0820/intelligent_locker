package com.deepglint.back_management.controller;

import com.alibaba.fastjson.JSON;
import com.deepglint.api.pojo.Courier;
import com.deepglint.back_management.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CourierController
 * @Description 快递员后台管理
 * @author: cdf
 * @Date: 2021-03-06 18:48
 **/
@RestController
public class CourierController {

	@Autowired
	private CourierService courierService;


	@PostMapping(value = "/insert")
	public String insertUser(@RequestBody Courier courier){
		courierService.insertCourier(courier);
		return "insert ok";
	}


	@DeleteMapping(value = "/delete/{courierId}")
	public String deleteUser(@PathVariable("courierId") int courierId){
		courierService.deleteCourier(courierId);
		return "delete ok";
	}


	@PutMapping(value = "/update")
	public String updateUser(@RequestBody Courier courier){
		courierService.updateCourier(courier);
		return "update ok";
	}


	@GetMapping(value = "/get/{courierId}")
	public String selectUser(@PathVariable("courierId") int courierId){
		Courier courier =  courierService.selectCourier(courierId);
		return JSON.toJSONString(courier);
	}

	@GetMapping(value = "/getAllUser")
	public List<Courier> getAllUser(){
		return courierService.getAllCourier();
	}
}
