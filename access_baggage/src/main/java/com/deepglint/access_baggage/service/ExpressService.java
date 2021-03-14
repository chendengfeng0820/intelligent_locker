package com.deepglint.access_baggage.service;

import com.deepglint.api.pojo.Order;
import com.deepglint.api.pojo.User;

public interface ExpressService {

	public User getUserInfo(Integer userId);

	public void insertOrder(Order order);

	public void insertUserOrder(int userId, long orderId);
}
