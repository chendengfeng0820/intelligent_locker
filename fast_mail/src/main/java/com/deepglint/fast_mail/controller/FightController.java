package com.deepglint.fast_mail.controller;

import com.alibaba.fastjson.JSON;
import com.deepglint.fast_mail.service.FightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName FightController
 * @Description redis 分布式锁来抢票
 * @author: cdf
 * @Date: 2021-02-19 01:17
 **/
@RestController
@Slf4j
public class FightController {

    private static String lockkey = "lockKey";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private FightService fightService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/fight/{orderId}", method = RequestMethod.GET)
    public String fight(@PathVariable("orderId") String orderId) {
        String clientId = UUID.randomUUID().toString();
        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(orderId));
        if (stock == 0){
            return "对不起,订单已被抢走";
        }
        try {
            Boolean result1 = stringRedisTemplate.opsForValue().setIfAbsent(lockkey, clientId, 30, TimeUnit.SECONDS);
            if (!result1) {
                return "有些火爆, 请稍后再试";
            }
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set(orderId, realStock + "");
                // 修改订单状态
                fightService.fight(Long.parseLong(orderId));
                log.info("扣减成功, 剩余库存: {}", realStock);

                // 将订单号发到消息队列  订单状态修改  通知用户
                HashMap hashMap = new HashMap();
                hashMap.put("orderId", orderId);
//              kafkaTemplate.send("topic2", JSON.toJSONString(hashMap));
                ListenableFuture future  = kafkaTemplate.send("topic2",JSON.toJSONString(hashMap));
//                future.addCallback(
//                        result -> log.info("生产者成功发送消息到topic:{} partition:{}的消息", result.getRecordMetadata().topic(), result.getRecordMetadata().partition()),
//                        ex -> logger.error("生产者发送消失败，原因：{}", ex.getMessage()));
//                );
            } else {
                return ("库存不够, 扣减失败");
            }
        } finally {
            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockkey))){
                stringRedisTemplate.delete(lockkey);
            }
        }
        return "end";
    }
}
