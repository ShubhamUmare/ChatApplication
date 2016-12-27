package com.Project2BackEnd.dao;

import java.util.List;

import com.Project2BackEnd.model.User;

public interface UserDao {
	List<User>getAllUser();
	User authenticate(User user);
	void updateUser(User user);
	User registerUser(User user);



}
