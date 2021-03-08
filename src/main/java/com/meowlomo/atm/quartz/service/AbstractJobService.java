package com.meowlomo.atm.quartz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.quartz.model.JobDescriptor;

@Component
public abstract class AbstractJobService implements JobService {

    private final Logger logger = LoggerFactory.getLogger(AbstractJobService.class);

    @Autowired
    protected Scheduler scheduler;

    @Autowired
    protected TriggerService triggerService;

    private JobDescriptor descriptor(JobKey key) {
        try {
            JobDetail job = scheduler.getJobDetail(key);
            if (Objects.nonNull(job)) {
                JobDescriptor jobDes = JobDescriptor.buildDescriptor(job, scheduler.getTriggersOfJob(key));
                jobDes.setTriggerDescriptors(triggerService.setTriggersStatus(jobDes.getTriggerDescriptors()));
                return jobDes;
            }
            logger.warn("Could not find job with key - {}", key);
            return null;
        }
        catch (SchedulerException e) {
            logger.error("Could not find job with key - {} due to error - {}", key, e.getLocalizedMessage());
            return null;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract JobDescriptor scheduleJob(JobDescriptor descriptor);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobDescriptor> getAllJobs() {
        try {
            Set<JobKey> keys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
            List<JobDescriptor> jobs = new ArrayList<JobDescriptor>();
            for (JobKey key : keys) {
                JobDetail jobDetail = scheduler.getJobDetail(key);
                JobDescriptor jobDes = JobDescriptor.buildDescriptor(jobDetail, scheduler.getTriggersOfJob(key));
                jobDes.setTriggerDescriptors(triggerService.setTriggersStatus(jobDes.getTriggerDescriptors()));
                jobs.add(jobDes);
            }
            return jobs;
        }
        catch (SchedulerException e) {
            logger.error("Could not find any jobs due to error - {}", e.getLocalizedMessage(), e);
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobDescriptor> getGroupJobs(String group) {
        try {
            Set<JobKey> keys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group));
            List<JobDescriptor> jobs = new ArrayList<JobDescriptor>();
            for (JobKey key : keys) {
                JobDetail jobDetail = scheduler.getJobDetail(key);
                JobDescriptor jobDes = JobDescriptor.buildDescriptor(jobDetail, scheduler.getTriggersOfJob(key));
                jobDes.setTriggerDescriptors(triggerService.setTriggersStatus(jobDes.getTriggerDescriptors()));
                jobs.add(jobDes);
            }
            return jobs;
        }
        catch (SchedulerException e) {
            logger.error("Could not find any jobs due to error - {}", e.getLocalizedMessage(), e);
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public JobDescriptor getJob(String jobName, String jobGroup) {
        JobKey jobkey = new JobKey(jobName, jobGroup);
        return this.descriptor(jobkey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean updateJob(String jobName, String jobGroup, JobDescriptor descriptor);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteJob(String jobName, String jobGroup) {
        try {
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
            logger.info("Deleted job with key - {}.{}", jobName, jobGroup);
            return true;
        }
        catch (SchedulerException e) {
            logger.error("Could not delete job with key - {}.{} due to error - {}", jobName, jobGroup,
                    e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean pauseJob(String jobName, String jobGroup) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
            logger.info("Paused job with key - {}.{}", jobName, jobGroup);
            return true;
        }
        catch (SchedulerException e) {
            logger.error("Could not pause job with key - {}.{} due to error - {}", jobName, jobGroup,
                    e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean resumeJob(String jobName, String jobGroup) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
            logger.info("Resumed job with key - {}.{}", jobName, jobGroup);
            return true;
        }
        catch (SchedulerException e) {
            logger.error("Could not resume job with key - {}.{} due to error - {}", jobName, jobGroup,
                    e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean startJobNow(String jobName, String jobGroup) {
        logger.info("Request received for starting job now.");

        String jobKey = jobName;
        String groupKey = "SampleGroup";

        JobKey jKey = new JobKey(jobKey, groupKey);
        logger.info("Parameters received for starting job now : jobKey :" + jobKey);
        try {
            scheduler.triggerJob(jKey);
            logger.info("Job with jobKey :" + jobKey + " started now succesfully.");
            return true;
        }
        catch (SchedulerException e) {
            logger.error("SchedulerException while starting job now with key :{} message :{}", jobName,
                    e.getLocalizedMessage(), e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isJobRunning(String jobName, String jobGroup) {
        logger.info("Request received to check if job is running now : jobKey : {} , group : {}", jobName, jobGroup);
        try {

            List<JobExecutionContext> currentJobs = scheduler.getCurrentlyExecutingJobs();
            if (currentJobs != null) {
                for (JobExecutionContext jobCtx : currentJobs) {
                    String jobNameDB = jobCtx.getJobDetail().getKey().getName();
                    String groupNameDB = jobCtx.getJobDetail().getKey().getGroup();
                    if (jobName.equalsIgnoreCase(jobNameDB) && jobGroup.equalsIgnoreCase(groupNameDB)) { return true; }
                }
            }
            return false;
        }
        catch (SchedulerException e) {
            logger.error("SchedulerException while checking job with key :{} group : {} is running. error message : {}",
                    jobName, jobGroup, e.getLocalizedMessage(), e);
            return false;
        }
    }

    @Override
    public boolean checkJobExists(String jobName, String jobGroup) {
        try {
            return scheduler.checkExists(JobKey.jobKey(jobName, jobGroup));
        }
        catch (SchedulerException e) {
            logger.error("SchedulerException while checking job exist with key :{} message :{}", jobName,
                    e.getLocalizedMessage(), e.getMessage());
            return false;
        }
    }

    @Override
    public String getJobState(String jobName, String jobGroup) {
        logger.info("JobServiceImpl.getJobState()");

        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobDetail.getKey());
            if (triggers != null && triggers.size() > 0) {
                for (Trigger trigger : triggers) {
                    TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());

                    if (TriggerState.PAUSED.equals(triggerState)) {
                        return "PAUSED";
                    }
                    else if (TriggerState.BLOCKED.equals(triggerState)) {
                        return "BLOCKED";
                    }
                    else if (TriggerState.COMPLETE.equals(triggerState)) {
                        return "COMPLETE";
                    }
                    else if (TriggerState.ERROR.equals(triggerState)) {
                        return "ERROR";
                    }
                    else if (TriggerState.NONE.equals(triggerState)) {
                        return "NONE";
                    }
                    else if (TriggerState.NORMAL.equals(triggerState)) { return "SCHEDULED"; }
                }
            }
        }
        catch (SchedulerException e) {
            logger.error("SchedulerException while checking job with name and group exist - message : {}",
                    e.getLocalizedMessage(), e);
        }
        return null;
    }

    @Override
    public List<JobKey> getJobKeys(String jobGroup) {
        try {
            Set<JobKey> keys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroup));
            return new ArrayList<>(keys);
        }
        catch (SchedulerException e) {
            logger.error("SchedulerException while getting job keys from scheduler by group : {}", jobGroup, e);
            return new ArrayList<JobKey>();
        }
    }

}