package com.deepglint.access_baggage.mapper;


import com.deepglint.api.pojo.Baggage;
import com.deepglint.api.pojo.Cabinet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface StoreMapper {

    public List<Cabinet> getAll();

    public Cabinet getReserve(int userId);

    public List<Cabinet> using(int userId);

    public void store(@Param("userId")Integer userId, @Param("cabinetId") Integer cabinetId, @Param("startTime")Date startTime);

    public void modifyCabinet(@Param("cabinetId") Integer cabinetId, @Param("stopTime")Date stopTime);

    public void deleteCabinet(@Param("cabinetId") Integer cabinet);


}
