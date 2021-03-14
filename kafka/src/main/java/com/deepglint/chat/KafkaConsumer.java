package com.deepglint.chat;

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
public class KafkaConsumer {

	@Autowired
	private WebSocket webSocket;

	// 消费监听
	@KafkaListener(topics = {"topic1"})
	public void onMessage2(ConsumerRecord<?, ?> record) {
		webSocket.onMessage(record.value().toString());
	}
}