package com.deepglint.pre_store.service.impl;

import com.deepglint.api.pojo.Cabinet;
import com.deepglint.pre_store.mapper.AvailableCabinetMapper;
import com.deepglint.pre_store.service.AvailableCabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AvailableCabinetServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2021-04-03 19:37
 **/
@Service
public class AvailableCabinetServiceImpl implements AvailableCabinetService {

    @Autowired
    private AvailableCabinetMapper availableCabinetMapper;

    @Override
    public List<Cabinet> cabinetList(int cabinetTotalId) {
        return availableCabinetMapper.cabinetList(cabinetTotalId);
    }
}
