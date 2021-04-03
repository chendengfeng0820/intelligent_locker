package com.deepglint.pre_store.config;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName SendMessageConfig
 * @Description TODO
 * @author: cdf
 * @Date: 2021-04-04 00:49
 **/
@Slf4j
@Component
public class SendMessageConfig {


    public String sendSms(String telephone) {

        // 获取验证码code
        String code = "123456";


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
