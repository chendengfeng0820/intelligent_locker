package com.deepglint.access_baggage.service;

import com.deepglint.api.pojo.Cabinet;

import java.util.Date;
import java.util.List;

public interface StoreService {

    public List<Cabinet> getAll();

    public Cabinet getReserve(int userId);

    public List<Cabinet> using(int userId);

    public void store(Integer userId, Integer cabinetId, Date startTime);

    public void modifyCabinet(Integer cabinetId, Date stopTime);

    public void deleteCabinet(Integer cabinet);
}
