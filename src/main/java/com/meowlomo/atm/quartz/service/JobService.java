package com.meowlomo.atm.quartz.service;

import java.util.List;

import org.quartz.JobKey;

import com.meowlomo.atm.quartz.model.JobDescriptor;
import com.meowlomo.atm.quartz.model.TriggerDescriptor;

public interface JobService {	
	JobDescriptor scheduleJob(JobDescriptor jobDescriptor);
	JobDescriptor scheduleJob(JobDescriptor jobDescriptor, TriggerDescriptor triggerDescriptor);
	List<JobDescriptor> getAllJobs();
	List<JobDescriptor> getGroupJobs(String group);
	JobDescriptor getJob(String jobName, String jobGroup);
	boolean updateJob(String jobName, String jobGroup, JobDescriptor descriptor);
	boolean deleteJob(String jobName, String jobGroup);
	boolean pauseJob(String jobName, String jobGroup);
	boolean resumeJob(String jobName, String jobGroup);
	boolean startJobNow(String jobName, String jobGroup);
	boolean isJobRunning(String jobName, String jobGroup);
	boolean checkJobExists(String jobName, String jobGroup);
	String getJobState(String jobName, String jobGroup);
	List<JobKey> getJobKeys(String jobGroup);
}
