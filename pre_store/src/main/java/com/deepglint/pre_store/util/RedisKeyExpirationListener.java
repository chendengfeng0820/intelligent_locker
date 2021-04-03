package com.deepglint.pre_store.util;

import com.deepglint.api.util.RedisUtil;
//import com.deepglint.pre_store.config.SendMessageConfig;
import com.deepglint.pre_store.config.SendMessageConfig;
import com.deepglint.pre_store.service.AvailableCabinetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisKeyExpirationListener
 * @Description TODO
 * @author: cdf
 * @Date: 2021-04-03 23:46
 **/

@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AvailableCabinetService availableCabinetService;

    @Autowired
    private SendMessageConfig sendMessageConfig;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * redis key失效，监听
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 业务处理 , 注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        log.info("柜子编号 {} 预定时间已到", expiredKey);
        redisUtil.set(expiredKey, 0);
        String telephone = availableCabinetService.sendMessage(Integer.valueOf(expiredKey));
        availableCabinetService.updateStatus(Integer.valueOf(expiredKey));
        log.info("预定过期柜子 {} 状态修改完毕", expiredKey);
        sendMessageConfig.sendSms(telephone);
        log.info("短信发送成功, 用户手机号 {}", telephone);
    }
}
