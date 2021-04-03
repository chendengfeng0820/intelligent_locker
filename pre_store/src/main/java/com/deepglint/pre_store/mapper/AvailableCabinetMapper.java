package com.deepglint.pre_store.mapper;

import com.deepglint.api.pojo.Cabinet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AvailableCabinetMapper
 * @Description TODO
 * @author: cdf
 * @Date: 2021-04-03 17:29
 **/
@Mapper
@Repository
public interface AvailableCabinetMapper {

    List<Cabinet> cabinetList(int cabinetTotalId);

    void subscribe(@Param("cabinetId") int cabinetId, @Param("userId")int userId, @Param("startTime") Date startTime);

    void updateStatus(int cabinetId);

    String sendMessage(int cabinetId);

}
