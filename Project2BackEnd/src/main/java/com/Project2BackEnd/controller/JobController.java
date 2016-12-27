package com.Project2BackEnd.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project2BackEnd.dao.JobDao;
import com.Project2BackEnd.model.Job;
import com.Project2BackEnd.model.User;
import com.Project2BackEnd.service.JobService;

import com.Project2BackEnd.model.Error;;

@RestController
public class JobController {
	@Autowired
	public JobService jobService;
	
	
	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}
	
	@Autowired
	private JobDao jobDao;
	
	
	
	public JobDao getJobDao() {
		return jobDao;
	}

	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	@RequestMapping(value="/postJob",method=RequestMethod.POST)
	public ResponseEntity<?>postJob(@RequestBody Job job,HttpSession session){
		User user=(User) session.getAttribute("user");
		if(user==null){
			Error error=new Error(1,"Unauthorized user..login using valid credentials");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);		
		}
		else
		{
			job.setPostedOn(new Date());
			String role=user.getRole();
			if(role.equals("admin")){
				jobDao.postJob(job);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else
			{
				Error error=new Error(2,"Only Admin can post new Jobs..");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
		}
		
	}
	
	@RequestMapping(value="/getAllJobs", method=RequestMethod.GET)
	public @ResponseBody List<Job> getAllJobs(){
		return jobService.getAllJobs();
	}
	
	public ResponseEntity<?> getAllJobs(HttpSession session)
	{
		User user=(User)session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(1,"Unauthorized User....Ligin with valid credentials");
			return new ResponseEntity<Error>(error,HttpStatus.OK);
		}
		System.out.println("USER OBJECT"+ user.getRole());
		List<Job> jobs=jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAllJobs/{jobId}",method=RequestMethod.GET)
	public ResponseEntity<Job> getJobById(@PathVariable(value="jobId") int jobId)
	{
		Job job=jobService.getJobById(jobId);
		if(job==null)
		{
			return new ResponseEntity<Job>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Job>(HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/job/{jobId}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteJob(@PathVariable int jobId){

		Job job=jobService.getJobById(jobId);
		if(job==null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		jobService.deleteJob(jobId);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
}
