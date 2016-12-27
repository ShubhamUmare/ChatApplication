package com.Project2BackEnd.service;

import java.util.List;

import com.Project2BackEnd.model.Job;

public interface JobService {
	
	List<Job> getAllJobs();
	void postJob (Job job);
	void deleteJob( int jobId);
	Job getJobById(int jobId);
}
