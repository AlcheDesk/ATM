package com.meowlomo.atm.quartz.job;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.quartz.InterruptableJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.external.ems.api.EMSApiService;
import com.meowlomo.atm.external.ems.model.Job;
import com.meowlomo.atm.external.ems.service.EMSJobService;

@Component
public class RunSetSchedulerJob extends QuartzJobBean implements InterruptableJob {

    private final Logger logger = LoggerFactory.getLogger(RunSetSchedulerJob.class);

    @Autowired
    private EMSJobService jobService;
    @Autowired
    private EMSApiService emsApiService;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @SuppressWarnings("unused")
    private volatile boolean toStopFlag = true;

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        logger.info("Stopping thread... ");
        toStopFlag = false;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        logger.info("Schedule RunSet Execution Job started with key :" + key.getName() + ", Group :" + key.getGroup()
                + " , Thread Name :" + Thread.currentThread().getName() + " ,Time now :" + new Date());
        ObjectMapper mapper = jacksonConverter.getObjectMapper();
        JobDataMap dataMap = context.getMergedJobDataMap();
        Object runSetObject = dataMap.get("runSet");
        Object runObject = dataMap.get("run");
        Run run = mapper.convertValue(runObject, Run.class);
        RunSet runSet = mapper.convertValue(runSetObject, RunSet.class);
        boolean result = this.executeRunSet(runSet, run);
        logger.info("Schedule RunSet Execution Job started with key :" + key.getName() + ", Group :" + key.getGroup()
                + " finished with result :" + result);
        logger.info("Thread: " + Thread.currentThread().getName() + " stopped" + " ,Time now :" + new Date());
    }

    private boolean executeRunSet(RunSet runSet, Run run) {
        Job job = jobService.buildJobForRunSet(runSet, run);
        if (job != null && run != null && run.getType() != null) {
            try {
                // insert the jobs
                List<Job> insertedJob = jobService.insertRecords(Arrays.asList(job), run);
                if (insertedJob.size() == 1) {                  
                    MeowlomoResponse response = emsApiService.sendJobsToEMS(insertedJob);
                    if (response != null) { return true; }
                }
                else {
                    logger.error("Schedule RunSet Execution Job chould not be insert to DB. RunSet ID:" + runSet.getId()
                            + ", Run :" + run);
                }
            }
            catch (JsonProcessingException e) {
                logger.error("Schedule RunSet Execution Job for RunSet ID:" + runSet.getId() + ", Run :" + run, e);
            }
        }
        else {
            logger.error("Schedule RunSet Execution Job could not craete job object from RunSet :" + runSet + ", Run :"
                    + run);
        }
        return false;
    }
}
