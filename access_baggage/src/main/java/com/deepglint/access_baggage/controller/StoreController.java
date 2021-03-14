package com.deepglint.access_baggage.controller;

import com.alibaba.fastjson.JSON;
import com.deepglint.access_baggage.service.StoreService;
import com.deepglint.api.pojo.Baggage;
import com.deepglint.api.pojo.Cabinet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;

/**
 * @ClassName StoreController
 * @Description 存包相关操作
 * @author: cdf
 * @Date: 2021-02-17 15:37
 **/
@RestController
@Slf4j
public class StoreController {

    @Autowired
    private StoreService storeService;

    /**
     * 查看预定信息
     * @param userId  用户id
     * @return
     */
    @RequestMapping(value = "/getReserve")
    public String getReserve(@RequestParam Integer userId){
        Cabinet cabinet = storeService.getReserve(userId);
        return JSON.toJSONString(cabinet);
    }


    @RequestMapping("/getAll")
    public String getAll(){
        List a = storeService.getAll();
        return JSON.toJSONString(a);
    }

    /**
     * 正在使用的信息
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/getUsing", method = RequestMethod.GET)
    public String getUsing(@RequestParam Integer userId){
        List<Cabinet> usingBaggages = storeService.using(userId);
        System.out.println(usingBaggages.toString());
        return JSON.toJSONString(usingBaggages);
    }

    /**
     * 下单存储物品
     * @param cabinetId 储物柜id
     * @param userId    用户id
     * @return
     */
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@RequestParam("cabinetId") Integer cabinetId, @RequestParam("userId") Integer userId){
        storeService.store(userId, cabinetId, new Date());
        return "ok";
    }

    /**
     * 取走行李
     * @param cabinetId 储物柜号码
     * @return
     */
    @Transactional
    @RequestMapping(value = "/take/{cabinetId}", method = RequestMethod.PUT)
    public String take(@PathVariable("cabinetId") Integer cabinetId){
        storeService.modifyCabinet(cabinetId, new Date());
//        System.out.println(1 / 0);  测试事务是否生效
        storeService.deleteCabinet(cabinetId);
        return "ok";
    }
}
