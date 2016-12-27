package com.Project2BackEnd.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.Project2BackEnd.dao.UserDao;
import com.Project2BackEnd.model.User;
import com.Project2BackEnd.service.UserService;

import ch.qos.logback.core.net.SyslogOutputStream;

import com.Project2BackEnd.model.Error;
@RestController
public class UserController {
	Logger logger=LoggerFactory.getLogger(this.getClass());
@Autowired
private UserDao userDao;
@Autowired
private UserService userService;

public UserService getUserService() {
	return userService;
}

public void setUserService(UserService userService) {
	this.userService = userService;
}

//isOnline - set true when the user login
//isOnline -set false when the user logout
@RequestMapping(value="/login",method=RequestMethod.POST)
public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
	// ? means it can return any type of object [Error, User]
	
	// the method login has to return any Type  
	//if the user is invalid - return Error object with status code
	//if the user is valid  - return User object with status code
	logger.debug("Entering USERCONTROLLER::LOGIN");
	User validUser=userDao.authenticate(user);
	if(validUser==null){
		logger.debug("ValidUser is null");
		Error error=new Error(1,"Username and password doesnt exists...");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); //401
	}
	else{
		session.setAttribute("user", validUser);
		validUser.setOnline(true);
		userDao.updateUser(validUser); // to update online status in db
		logger.debug("ValidUser is not null");
		return new ResponseEntity<User>(validUser,HttpStatus.OK);//200
	}
}

@RequestMapping(value="/register",method=RequestMethod.POST)
public ResponseEntity<?>registerUser(@RequestBody User user){
	logger.debug("USERCONTROLLER=REGISTER"+user);
	user.setStatus(true);
	user.setOnline(false);
	User savedUser=userDao.registerUser(user);
	logger.debug("User id Generated is"+savedUser.getId());
	if(savedUser.getId()==0)
	{
		Error error=new Error(2,"Couldnt insert user details");
	
	return new ResponseEntity<Error>(error,HttpStatus.CONFLICT);
	}
	else
		return new ResponseEntity<User>(savedUser,HttpStatus.OK);
	
}

@RequestMapping(value="/users",method=RequestMethod.GET)
public @ResponseBody List<User> getAllUser() {
	System.out.println(userService.getAllUser());
	return userService.getAllUser();
}


@RequestMapping(value="/logout", method=RequestMethod.PUT)
public ResponseEntity<?> logout(HttpSession session){
	User user=(User)session.getAttribute("user");
	if(user!=null){
			user.setOnline(false);
			userDao.updateUser(user);
	}
	session.removeAttribute("user");
	session.invalidate();
	return new ResponseEntity<Void>(HttpStatus.OK);
}
}
