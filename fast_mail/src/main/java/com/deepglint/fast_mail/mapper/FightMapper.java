package com.deepglint.fast_mail.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

/**
 * @ClassName FightMapper
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-19 01:19
 **/
@Mapper
@Repository
public interface FightMapper {

	public void fight(Long orderId);

	public int getUserId(Long orderId);

	public String getUserTelephone(int userId);

}
