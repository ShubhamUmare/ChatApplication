package com.Project2BackEnd.dao;

import java.util.List;

import com.Project2BackEnd.model.Job;


public interface JobDao {
void postJob (Job job);
public List<Job> getAllJobs();
Job getJobById(int jobId);
void deleteJob( int jobId);
}
