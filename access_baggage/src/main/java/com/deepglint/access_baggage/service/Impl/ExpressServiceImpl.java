package com.deepglint.access_baggage.service.Impl;

import com.deepglint.access_baggage.mapper.ExpressMapper;
import com.deepglint.access_baggage.service.ExpressService;
import com.deepglint.api.pojo.Order;
import com.deepglint.api.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ExpressServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-18 10:39
 **/
@Service
public class ExpressServiceImpl implements ExpressService {

	@Autowired
	private ExpressMapper expressMapper;

	@Override
	public User getUserInfo(Integer userId) {
		return expressMapper.getUserInfo(userId);
	}

	@Override
	public void insertOrder(Order order) {
		expressMapper.insertOrder(order);
	}

	@Override
	public void insertUserOrder(int userId, long orderId) {
		expressMapper.insertUserOrder(userId, orderId);
	}
}
