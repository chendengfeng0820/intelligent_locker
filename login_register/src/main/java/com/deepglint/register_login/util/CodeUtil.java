package com.deepglint.register_login.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @ClassName CodeUtil
 * @Description 生成四位随机验证码
 * @author: cdf
 * @Date: 2020-06-16 23:33
 **/
@Component
@Slf4j
public class CodeUtil {

	public String generateCode() {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			stringBuilder = stringBuilder.append(random.nextInt(9));
		}
		return JSON.toJSONString(stringBuilder);
	}
}
