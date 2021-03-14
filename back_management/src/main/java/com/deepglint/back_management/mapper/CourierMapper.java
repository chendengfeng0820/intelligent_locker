package com.deepglint.back_management.mapper;

import com.deepglint.api.pojo.Courier;
import com.deepglint.api.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourierMapper {

	void insertCourier(Courier courier);

	void deleteCourier(int courierId);

	void updateCourier(Courier courier);

	Courier selectCourier(int courierId);

	List<Courier> getAllCourier();
}
