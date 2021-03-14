package com.deepglint.fast_mail.service.Impl;

import com.deepglint.fast_mail.mapper.FightMapper;
import com.deepglint.fast_mail.service.FightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName FightServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-19 01:20
 **/
@Service
public class FightServiceImpl implements FightService {

	@Autowired
	private FightMapper fightMapper;

	@Override
	public void fight(Long orderId) {
		fightMapper.fight(orderId);
	}

	@Override
	public int getUserId(Long orderId) {
		int userId = fightMapper.getUserId(orderId);
		return userId;
	}

	@Override
	public String getUserTelephone(int userId) {
		String userTelephone = fightMapper.getUserTelephone(userId);
		return userTelephone;
	}
}
