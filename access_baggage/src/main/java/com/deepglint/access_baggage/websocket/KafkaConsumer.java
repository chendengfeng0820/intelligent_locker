package com.deepglint.access_baggage.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deepglint.api.util.RedisUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName KafkaConsumer
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-18 17:16
 **/

@Component
public class KafkaConsumer {

	@Autowired
	private WebSocket webSocket;

	@Autowired
    private RedisUtil redisUtil;

	// 消费监听
	@KafkaListener(topics = {"topic1"})
	public void onMessage2(ConsumerRecord<?, ?> record) {
		String value = record.value().toString();
		JSONObject jsonData = JSONObject.parseObject(value);
		String orderId = jsonData.getString("orderId");
		redisUtil.set(orderId,1);
		webSocket.onMessage(record.value().toString());
	}
}