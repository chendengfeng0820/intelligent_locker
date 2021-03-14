package com.deepglint.back_management.service.Impl;

import com.deepglint.api.pojo.User;
import com.deepglint.back_management.mapper.UserMapper;
import com.deepglint.back_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2021-03-06 16:49
 **/
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public void deleteUser(int userId) {
		userMapper.deleteUser(userId);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public User selectUser(int userId) {
		return userMapper.selectUser(userId);
	}

	@Override
	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}
}
