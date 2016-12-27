package com.Project2BackEnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project2BackEnd.dao.JobDao;
import com.Project2BackEnd.model.Job;
@Service
public class JobServiceImpl implements JobService{
	@Autowired
	public JobDao jobDao;
	

	public JobDao getJobDao() {
		return jobDao;
	}


	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}


	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		return jobDao.getAllJobs();
	}


	public void postDao(Job job) {
		// TODO Auto-generated method stub
		jobDao.postJob(job);
	}


	public void postJob(Job job) {
		// TODO Auto-generated method stub
		jobDao.postJob(job);
	}


	

	public void deleteJob(int jobId) {
		// TODO Auto-generated method stub
		jobDao.deleteJob(jobId);
	}


	public Job getJobById(int jobId) {
		// TODO Auto-generated method stub
		return jobDao.getJobById(jobId);
	}

	
}
