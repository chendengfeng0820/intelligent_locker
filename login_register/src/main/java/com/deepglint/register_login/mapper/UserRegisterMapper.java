package com.deepglint.register_login.mapper;

import com.deepglint.api.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName UserRegisterMapper
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-16 12:56
 **/
@Mapper
@Repository
public interface UserRegisterMapper {

    public int userRegister(User user);

    public String login(String telephone);

//    public List<User> findAll();

}
