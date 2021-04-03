package com.deepglint.pre_store.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LocaltionContoller
 * @Description TODO
 * @author: cdf
 * @Date: 2021-04-03 15:33
 **/
@RestController
@Slf4j
public class LocaltionContoller {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *
     * @param cabinetTotalId 柜子号
     * @param longitude      经度
     * @param latitude       纬度
     * @return
     */
    @RequestMapping("/addCabinetTotal")
    public String addCabinetTotal(@RequestParam("cabinetTotalId") int cabinetTotalId, @RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude){
        redisTemplate.opsForGeo().add("cabinetTotal",new Point(longitude,latitude),cabinetTotalId);
        return "ok";
    }

    /**
     *
     * @param distance      范围距离
     * @param longitude     经度
     * @param latitude      纬度
     * @return
     */
    @RequestMapping("/cabinetTotalList")
    public String cabinetTotalList(@RequestParam("distance") double distance, @RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude){
        Circle circle = new Circle(new Point(longitude, latitude), new Distance(distance, Metrics.KILOMETERS));
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo().radius("cabinetTotal", circle, args);
        return JSON.toJSONString(results);
    }


}
