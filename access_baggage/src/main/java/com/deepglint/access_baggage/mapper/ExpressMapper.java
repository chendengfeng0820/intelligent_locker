package com.deepglint.access_baggage.mapper;

import com.deepglint.api.pojo.Cabinet;
import com.deepglint.api.pojo.Order;
import com.deepglint.api.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExpressMapper {

	/**
	 * 根据 userId 获取用户信息
	 * @param userId
	 * @return
	 */
	public User getUserInfo(Integer userId);

	/**
	 * 添加订单信息
	 * @param order
	 */
	public void insertOrder(Order order);

	public void insertUserOrder(@Param("userId") int userId, @Param("orderId") Long orderId);
}
