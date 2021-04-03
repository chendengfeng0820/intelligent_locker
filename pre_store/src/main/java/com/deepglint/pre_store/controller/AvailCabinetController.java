package com.deepglint.pre_store.controller;

import com.alibaba.fastjson.JSON;
import com.deepglint.api.pojo.Cabinet;
import com.deepglint.pre_store.service.AvailableCabinetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName AvailCabinetController
 * @Description TODO
 * @author: cdf
 * @Date: 2021-04-03 17:27
 **/
@RestController
@Slf4j
public class AvailCabinetController {

    @Autowired
    private AvailableCabinetService availableCabinetService;

    @GetMapping("/availableCabinetList/{cabinetTotalId}")
    public String availableCabinetList(@PathVariable("cabinetTotalId") int cabinetTotalId){
        List<Cabinet> cabinets = availableCabinetService.cabinetList(cabinetTotalId);
        return JSON.toJSONString(cabinets);
    }
}
