package com.deepglint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestController
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-20 20:20
 **/
@RestController
public class TestController {

    private static String lockkey = "lockKey";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping("/deduct_stock")
    public String deductStock() {

        String clientId = UUID.randomUUID().toString();

//        try {
//            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockkey, "is_using");
//            stringRedisTemplate.opsForValue().setIfAbsent(lockkey, clientId, 30, TimeUnit.SECONDS);
//
//            if (!result) {
//                return "有些火爆, 请稍后再来";
//            }
//            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//            if (stock > 0) {
//                int realStock = stock - 1;
//                stringRedisTemplate.opsForValue().set("stock", realStock + "");
//                System.out.println("扣减成功, 剩余库存: " + realStock);
//            } else {
//                System.out.println("库存不够, 扣减失败");
//            }
//        } finally {
//            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockkey))){
//                stringRedisTemplate.delete(lockkey);
//            }
//        }
        return "end";
    }
}
