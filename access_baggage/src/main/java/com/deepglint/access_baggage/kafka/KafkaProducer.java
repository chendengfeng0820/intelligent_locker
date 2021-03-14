//package com.deepglint.access_baggage.kafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @ClassName KafkaProducer
// * @Description TODO
// * @author: cdf
// * @Date: 2021-02-18 17:12
// **/
//@RestController
//public class KafkaProducer {
//
//	@Autowired
//	private KafkaTemplate kafkaTemplate;
//
//	@GetMapping("/kafka/normal/{message}")
//	public void sendMessage1(@PathVariable("message") String normalMessage) {
//		kafkaTemplate.send("topic1", normalMessage);
//	}
//}