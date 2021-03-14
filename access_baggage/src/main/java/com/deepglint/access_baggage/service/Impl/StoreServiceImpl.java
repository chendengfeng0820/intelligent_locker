package com.deepglint.access_baggage.service.Impl;

import com.deepglint.access_baggage.mapper.StoreMapper;
import com.deepglint.access_baggage.service.StoreService;
import com.deepglint.api.pojo.Baggage;
import com.deepglint.api.pojo.Cabinet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName StoreServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-17 16:03
 **/
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public List<Cabinet> getAll() {
        return storeMapper.getAll();
    }

    @Override
    public Cabinet getReserve(int userId) {
        Cabinet cabinet = storeMapper.getReserve(userId);
        return cabinet;
    }

    @Override
    public List<Cabinet> using(int userId) {
        List<Cabinet> using = storeMapper.using(userId);
        return using;
    }

    @Override
    public void store(Integer userId, Integer cabinetId, Date startTime) {
        storeMapper.store(userId, cabinetId, startTime);
    }

    @Override
    public void modifyCabinet(Integer cabinetId, Date stopTime) {
        storeMapper.modifyCabinet(cabinetId, stopTime);
    }

    @Override
    public void deleteCabinet(Integer cabinet) {
        storeMapper.deleteCabinet(cabinet);
    }
}
