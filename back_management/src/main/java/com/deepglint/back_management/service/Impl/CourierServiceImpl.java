package com.deepglint.back_management.service.Impl;

import com.deepglint.api.pojo.Courier;
import com.deepglint.back_management.mapper.CourierMapper;
import com.deepglint.back_management.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CourierServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2021-03-06 18:46
 **/
@Service
public class CourierServiceImpl implements CourierService {

	@Autowired
	private CourierMapper courierMapper;

	@Override
	public void insertCourier(Courier courier) {
		courierMapper.insertCourier(courier);
	}

	@Override
	public void deleteCourier(int courierId) {
		courierMapper.deleteCourier(courierId);
	}

	@Override
	public void updateCourier(Courier courier) {
		courierMapper.updateCourier(courier);
	}

	@Override
	public Courier selectCourier(int courierId) {
		return courierMapper.selectCourier(courierId);
	}

	@Override
	public List<Courier> getAllCourier() {
		return courierMapper.getAllCourier();
	}
}
