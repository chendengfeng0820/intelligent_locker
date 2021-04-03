package com.deepglint.access_baggage.controller;

import com.alibaba.fastjson.JSON;
import com.deepglint.access_baggage.service.ExpressService;
import com.deepglint.api.pojo.Order;
import com.deepglint.api.pojo.User;
import com.deepglint.api.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * @ClassName ExpressController
 * @Description 用户快递下单
 * @author: cdf
 * @Date: 2021-02-18 10:22
 **/
@RestController
@Slf4j
public class ExpressController {

	@Autowired
	private SnowFlake snowFlake;

	@Autowired
	private KafkaTemplate kafkaTemplate;

	@Autowired
	private ExpressService expressService;

	@Transactional
	@RequestMapping(value = "/express", method = RequestMethod.POST)
	public String express(@RequestParam("userId") Integer userId, @RequestBody Order order){
		long expressId = snowFlake.nextId();
		User user = expressService.getUserInfo(userId);
		order.setOrderId(expressId);
		order.setUserId(userId);
		order.setExpectTime(new Timestamp(order.getExpectTime().getTime()));
		order.setOrderTime(new Timestamp(order.getOrderTime().getTime()));
		expressService.insertOrder(order);

		HashMap<String, Object> hashMap = new HashMap<>(9);
		hashMap.put("userId",user.getUserId());
		hashMap.put("telephone",user.getTelephone());
		hashMap.put("appName",user.getAppName());
		hashMap.put("orderId",order.getOrderId());
		hashMap.put("from",order.getFrom());
		hashMap.put("to",order.getTo());
		hashMap.put("expectTime",order.getExpectTime());
		hashMap.put("orderTime",order.getOrderTime());
		hashMap.put("status", order.getStatus());
        String result = JSON.toJSONString(hashMap);
        kafkaTemplate.send("topic1",result);
		return "ok";
	}

}
