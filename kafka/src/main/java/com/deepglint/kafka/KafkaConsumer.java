package com.deepglint.kafka;//package com.deepglint.access_baggage.kafka;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName KafkaConsumer
// * @Description TODO
// * @author: cdf
// * @Date: 2021-02-18 17:16
// **/
//@Component
//public class KafkaConsumer {
//	// 消费监听
//	@KafkaListener(topics = {"topic1"})
//	public void onMessage1(ConsumerRecord<?, ?> record) {
//		// 消费的哪个topic、partition的消息,打印出消息内容
//		System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
//	}
//}