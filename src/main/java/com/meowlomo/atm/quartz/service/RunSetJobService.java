package com.meowlomo.atm.quartz.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.meowlomo.atm.quartz.job.RunSetSchedulerJob;
import com.meowlomo.atm.quartz.model.JobDescriptor;
import com.meowlomo.atm.quartz.model.TriggerDescriptor;

@Service
public class RunSetJobService extends AbstractJobService{

	private final Logger logger = LoggerFactory.getLogger(RunSetJobService.class);

	@Override
	public JobDescriptor scheduleJob(JobDescriptor jobDescriptor) {
		String jobName = jobDescriptor.getName();
		String jobGroup = jobDescriptor.getGroup();
		try {
			if (scheduler.checkExists(JobKey.jobKey(jobName, jobGroup))) {
				throw new DataIntegrityViolationException("Job with Key '" + jobGroup + "." + jobName +"' already exists");
			}
			Map<String, Object> data = new LinkedHashMap<String, Object>();
			data.put("runSetId", Long.valueOf(jobName));
			
			jobDescriptor.setData(data);
			JobDetail jobDetail = jobDescriptor.buildJobDetail(RunSetSchedulerJob.class);
			Set<Trigger> triggersForJob = jobDescriptor.buildTriggers();
			logger.info("About to schedule run set job with key - {}", jobDetail.getKey());
			scheduler.scheduleJob(jobDetail, triggersForJob, false);
			logger.info("Job with key - {} schedule sucessfully", jobDetail.getKey());
			return jobDescriptor;
		} catch (SchedulerException e) {
			logger.error("Could not save job with key - {}.{} due to error - {}", jobName, jobGroup, e.getLocalizedMessage());
		}
		return null;
	}
	

	@Override
	public JobDescriptor scheduleJob(JobDescriptor jobDescriptor, TriggerDescriptor triggerDescriptor) {
        String jobName = jobDescriptor.getName();
        String jobGroup = jobDescriptor.getGroup();
        try {
            if (scheduler.checkExists(JobKey.jobKey(jobName, jobGroup))) {
                throw new DataIntegrityViolationException("Job with Key '" + jobGroup + "." + jobName +"' already exists");
            }
            Map<String, Object> data = new LinkedHashMap<String, Object>();
            data.put("runSetId", Long.valueOf(jobName));
            
            jobDescriptor.setData(data);
            JobDetail jobDetail = jobDescriptor.buildJobDetail(RunSetSchedulerJob.class);
            Set<Trigger> triggersForJob = jobDescriptor.buildTriggers();
            logger.info("About to schedule run set job with key - {}", jobDetail.getKey());
            scheduler.scheduleJob(jobDetail, triggersForJob, false);
            logger.info("Job with key - {} schedule sucessfully", jobDetail.getKey());
            return jobDescriptor;
        } catch (SchedulerException e) {
            logger.error("Could not save job with key - {}.{} due to error - {}", jobName, jobGroup, e.getLocalizedMessage());
        }
        return null;
	}

	@Override
	public boolean updateJob(String group, String name, JobDescriptor descriptor) {
		try {
			JobDetail oldJobDetail = scheduler.getJobDetail(JobKey.jobKey(name, group));
			if (Objects.nonNull(oldJobDetail)) {
				JobDataMap newJobDataMap = new JobDataMap(descriptor.getData());
				JobBuilder jb = oldJobDetail.getJobBuilder();
				JobDetail newJobDetail = jb.usingJobData(newJobDataMap).storeDurably().build();
				scheduler.addJob(newJobDetail, true);
				logger.info("Updated job with key - {}", newJobDetail.getKey());
				return true;
			}
			logger.warn("Could not find job with key - {}.{} to update", group, name);
			return false;
		} catch (SchedulerException e) {
			logger.error("SchedulerException while updating job with key - {}.{} to update due to error - {}", group, name, e.getLocalizedMessage());
			return false;
		}
	}

}

