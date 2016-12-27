package com.Project2BackEnd.implemention;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Project2BackEnd.dao.FileUploadDao;
import com.Project2BackEnd.model.UploadFile;

@Repository
public class FileUploadDaoImpl implements FileUploadDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	

	@Transactional
	public void save(UploadFile uploadFile) {
		// TODO Auto-generated method stub
		
	}

	public UploadFile getFile(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
