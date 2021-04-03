package com.deepglint.pre_store.mapper;

import com.deepglint.api.pojo.Cabinet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

    public List<Cabinet> cabinetList(int cabinetTotalId);

}
