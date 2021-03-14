package com.deepglint.register_login.config;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.deepglint.api.util.RedisUtil;
import com.deepglint.register_login.util.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SendSmsConfig
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-15 23:41
 **/
@Component
@Slf4j
public class SendSmsConfig {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CodeUtil codeUtil;

    public String sendSms(String telephone) {

        // 获取验证码code
        String code = codeUtil.generateCode();

        // redis手机号发送验证码次数限制
        if (redisUtil.hashKey(telephone)) {
            int times = (int) redisUtil.getHash(telephone, "times");
            if (times < 3) {
                //覆盖上一次的验证码
                redisUtil.hset(telephone,"code",code, TimeUnit.SECONDS.toSeconds(10000));
                //此手机号验证码发送次数 incrby +1
                redisUtil.haIncr(telephone, "times", 1);
            } else if(times == 3){
                //验证码发送次数等于三次，2小时时间限制
                redisUtil.expire(telephone,TimeUnit.HOURS.toHours(2));
                return JSON.toJSONString("对不起，您已连续发送超过三次，请五分钟后再试");
            }
        } else {
            //正常，设置验证码的过期时间
            redisUtil.hset(telephone, "code", code, TimeUnit.SECONDS.toSeconds(10000));
            redisUtil.hset(telephone, "times", 1);
            log.info("手机号: {}, 验证码: {}",telephone,code);
        }

        /**
         * 阿里云发送短信
         */
        CommonResponse response=null;
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FeeUqB8dQC9j2pFg44v", "cKOuyG8C6Zqr7t8oxh67vfzaiSdkOd");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", telephone);
        request.putQueryParameter("SignName", "移动储物柜");
        request.putQueryParameter("TemplateCode", "SMS_193237932");
        request.putQueryParameter("TemplateParam", "{\"code\":" + code + "}");
        try {
            response = client.getCommonResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(response);
    }
}
