package com.deepglint.back_management.mapper;


import com.deepglint.api.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

	void insertUser(User user);

	void deleteUser(int userId);

	void updateUser(User user);

	User selectUser(int userId);

	List<User> getAllUser();
}
