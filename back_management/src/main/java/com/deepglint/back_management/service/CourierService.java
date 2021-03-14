package com.deepglint.back_management.service;

import com.deepglint.api.pojo.Courier;

import java.util.List;

public interface CourierService {

	void insertCourier(Courier courier);

	void deleteCourier(int courierId);

	void updateCourier(Courier courier);

	Courier selectCourier(int courierId);

	List<Courier> getAllCourier();
}
