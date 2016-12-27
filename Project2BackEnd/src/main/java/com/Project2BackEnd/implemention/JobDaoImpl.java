package com.Project2BackEnd.implemention;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Project2BackEnd.dao.JobDao;
import com.Project2BackEnd.model.Job;

@Repository
public class JobDaoImpl implements JobDao {
@Autowired

private SessionFactory sessionFactory;

	public void postJob(Job job) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(job);
		session.getTransaction().commit();
		session.close();
	}

	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("From Job");
		List<Job> jobs=query.list();
		session.close();
		return jobs;
	}

	
	public void deleteJob(int jobId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Job job=(Job)session.get(Job.class,jobId);
		session.delete(job);
		session.getTransaction().commit();
		session.close();	
	}

	public Job getJobById(int jobId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Job job=session.get(Job.class,jobId);
		session.getTransaction().commit();
		session.close();
		return job;
	}

}
