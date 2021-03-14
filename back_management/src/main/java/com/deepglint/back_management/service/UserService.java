package com.deepglint.back_management.service;

import com.deepglint.api.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

	void insertUser(User user);

	void deleteUser(int userId);

	void updateUser(User user);

	User selectUser(int userId);

	List<User> getAllUser();

}
