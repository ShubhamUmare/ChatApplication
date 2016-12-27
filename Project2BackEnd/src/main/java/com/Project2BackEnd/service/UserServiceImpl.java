package com.Project2BackEnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project2BackEnd.dao.UserDao;
import com.Project2BackEnd.model.User;

@Service
public class UserServiceImpl implements UserService{
@Autowired

	private UserDao userDao;

	public UserDao getUserDao() {
	return userDao;
}

public void setUserDao(UserDao userDao) {
	this.userDao = userDao;
}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}

}
