package com.deepglint.fast_mail.controller;

import com.alibaba.fastjson.JSONObject;
import com.deepglint.fast_mail.service.FightService;
import com.deepglint.register_login.config.SendSmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName KafkaConsumer
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-18 17:16
 **/

@Component
@Slf4j
public class KafkaConsumer {

	@Autowired
	private FightService fightService;

	@Autowired
	private SendSmsConfig sendSmsConfig;


	// 消费监听
	@KafkaListener(topics = {"topic2"})
	public void onMessage(ConsumerRecord<?, ?> record) {
		String value = record.value().toString();
		JSONObject jsonData = JSONObject.parseObject(value);
		String orderId = jsonData.getString("orderId");
		int userId = fightService.getUserId(Long.parseLong(orderId));
		String telephone = fightService.getUserTelephone(userId);
		sendSmsConfig.sendSms(telephone);
		log.info("用户: {}, 订单号: {} 状态已经修改", userId, orderId);
	}
}