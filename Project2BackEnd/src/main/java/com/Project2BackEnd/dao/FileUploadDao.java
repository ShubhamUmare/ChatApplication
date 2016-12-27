package com.Project2BackEnd.dao;

import com.Project2BackEnd.model.UploadFile;

public interface FileUploadDao {

	void save(UploadFile uploadFile);
	UploadFile getFile(String username);
}
