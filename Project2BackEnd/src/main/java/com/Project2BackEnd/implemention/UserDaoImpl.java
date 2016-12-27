package com.Project2BackEnd.implemention;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Project2BackEnd.dao.UserDao;
import com.Project2BackEnd.model.User;
@Repository

public class UserDaoImpl implements UserDao{
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public User authenticate(User user) {
		logger.debug("USERDAOIMPL::AUTHENTICATE");
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(
		"from User where username=?  and password=?");
		//select * from User where username='smith' and password='123'
		query.setString(0, user.getUsername());
		query.setString(1, user.getPassword());
		User validUser=(User)query.uniqueResult();
		session.close();
		if(validUser!=null)
		{
			logger.debug("VALID USER IS:"+validUser.getUsername()+" "+ validUser.getRole()+" "+validUser.isOnline());
		}
		if(validUser==null)
		{
			logger.debug("Valid USER is null");
		}
		return validUser;
		
	}
	@Transactional
	public void updateUser(User user) {
		logger.debug("USERDAOIMPL::UPDATE");
		logger.debug("ISONLINE VALUE IS [BEFORE UPDATE]"+user.isOnline());
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		User existingUser=(User)session.get(User.class, user.getId());
		//update online status as true
		existingUser.setOnline(user.isOnline()); 
		
		session.update(existingUser);
		//session.flush();
		session.getTransaction().commit();
		session.close();
		logger.debug("IS ONLINE VALUE IS[AFTER UPDATE]"+existingUser.isOnline());
	}
	public User registerUser(User user) {
		logger.debug("USERDAOIMPL::resgisterUser");
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		// TODO Auto-generated method stub
		logger.debug("User id in Dao:"+user.getId());
		return user;
	}
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from User");
		List<User> users=query.list();
		session.close();
		return users;
	}

}
