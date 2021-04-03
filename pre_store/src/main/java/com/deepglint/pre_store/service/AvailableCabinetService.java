package com.deepglint.pre_store.service;

import com.deepglint.api.pojo.Cabinet;

import java.util.List;

/**
 * @ClassName AvailableCabinetService
 * @Description TODO
 * @author: cdf
 * @Date: 2021-04-03 19:37
 **/
public interface AvailableCabinetService {

    public List<Cabinet> cabinetList(int cabinetTotalId);

}
