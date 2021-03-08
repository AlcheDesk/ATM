package com.meowlomo.atm.quartz.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meowlomo.atm.core.model.Run;
import com.meowlomo.atm.core.model.RunSet;
import com.meowlomo.atm.core.service.referrence.RunSetReferenceService;
import com.meowlomo.atm.quartz.model.CronDetails;
import com.meowlomo.atm.quartz.model.JobDescriptor;
import com.meowlomo.atm.quartz.model.TriggerDescriptor;

@Service
public class TriggerServiceImpl implements TriggerService {

    private final Logger logger = LoggerFactory.getLogger(TriggerServiceImpl.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private RunSetReferenceService runSetReferenceService;

    @Autowired
    private TriggerService triggerService;

    @Value("${spring.jackson.time-zone}")
    private String timezone;

    @Override
    public TriggerDescriptor updateTrigger(String triggerName, String triggerGroup,
            TriggerDescriptor newTriggerDescriptor) {
        newTriggerDescriptor.setName(triggerName);
        newTriggerDescriptor.setGroup(triggerGroup);
        newTriggerDescriptor.setTimeZone(TimeZone.getTimeZone(timezone));
        Trigger olderTrigger;
        try {
            olderTrigger = scheduler.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroup));
            Trigger newTrigger = newTriggerDescriptor.buildTrigger();
            scheduler.rescheduleJob(olderTrigger.getKey(), newTrigger);
            return TriggerDescriptor.buildDescriptor(scheduler.getTrigger(newTrigger.getKey()));
        }
        catch (SchedulerException e) {
            logger.error("Error on updateing trigger. triggerkey : {}, trigger group : {}, message : {}", triggerName,
                    triggerGroup, e.getLocalizedMessage(), e);
            return null;
        }
    }

    @Override
    public TriggerDescriptor addTrigger(JobDescriptor jobDescriptor, TriggerDescriptor triggerDescriptor) {
        JobKey jobKey = JobKey.jobKey(jobDescriptor.getName(), jobDescriptor.getGroup());
        try {
            triggerDescriptor.setGroup(jobDescriptor.getGroup());
            triggerDescriptor.setTimeZone(TimeZone.getTimeZone(timezone));
            Trigger trigger = triggerDescriptor.buildTrigger();
            trigger.getScheduleBuilder();
            trigger = trigger.getTriggerBuilder().forJob(jobKey).build();

            // add the data map
            RunSet runSetObject = runSetReferenceService.selectByPrimaryKey(Long.valueOf(jobDescriptor.getName()));
            Run runObject = new Run();
            runObject.setType("PRODUCTION");
            runObject.setGroup("Default");
            trigger.getJobDataMap().put("runSet", runSetObject);
            trigger.getJobDataMap().put("run", runObject);

            Date date = scheduler.scheduleJob(trigger);
            if (date != null) {
                return TriggerDescriptor.buildDescriptor(scheduler.getTrigger(trigger.getKey()));
            }
            else {
                return null;
            }
        }
        catch (SchedulerException e) {
            logger.error("Could not find job with key - {} group - {} due to error - {}", jobDescriptor.getName(),
                    jobDescriptor.getGroup(), e.getLocalizedMessage(), e);
            return null;
        }
    }

    @Override
    public boolean removeTriggers(List<TriggerDescriptor> triggerDescriptors) {
        List<TriggerKey> triggerKeys = new ArrayList<TriggerKey>();
        for (TriggerDescriptor triggerDescriptor : triggerDescriptors) {
            triggerDescriptor.setTimeZone(TimeZone.getTimeZone(timezone));
            triggerKeys.add(TriggerKey.triggerKey(triggerDescriptor.getName(), triggerDescriptor.getGroup()));
        }
        try {
            return scheduler.unscheduleJobs(triggerKeys);
        }
        catch (SchedulerException e) {
            logger.error("SchedulerException while removing triggers. trigger keys : {}, message : {}", triggerKeys,
                    e.getLocalizedMessage(), e);
            return false;
        }
    }

    @Override
    public List<TriggerDescriptor> setTriggersStatus(List<TriggerDescriptor> triggerDescriptors) {
        List<TriggerDescriptor> returnList = new ArrayList<TriggerDescriptor>();
        for (TriggerDescriptor triggerDescriptor : triggerDescriptors) {
            if (triggerDescriptor.getCronExpression() != null) {
                triggerService.transferCronToCronDetails(triggerDescriptor);
            }
            if (triggerDescriptor.getStartTime() != null) {
                triggerService.transferStartTimeToStartTimeDetails(triggerDescriptor);
            }
            triggerDescriptor.setTimeZone(TimeZone.getTimeZone(timezone));
            returnList.add(this.injectStatus(triggerDescriptor));
        }
        return returnList;
    }

    private TriggerDescriptor injectStatus(TriggerDescriptor triggerDescriptor) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerDescriptor.getName(), triggerDescriptor.getGroup());
            if (scheduler.checkExists(triggerKey)) {
                TriggerState triggerState = scheduler.getTriggerState(triggerKey);
                if (TriggerState.PAUSED.equals(triggerState)) {
                    triggerDescriptor.setStatus("PAUSED");
                }
                else if (TriggerState.BLOCKED.equals(triggerState)) {
                    triggerDescriptor.setStatus("BLOCKED");
                }
                else if (TriggerState.COMPLETE.equals(triggerState)) {
                    triggerDescriptor.setStatus("COMPLETE");
                }
                else if (TriggerState.ERROR.equals(triggerState)) {
                    triggerDescriptor.setStatus("ERROR");
                }
                else if (TriggerState.NONE.equals(triggerState)) {
                    triggerDescriptor.setStatus("NONE");
                }
                else if (TriggerState.NORMAL.equals(triggerState)) {
                    triggerDescriptor.setStatus("SCHEDULED");
                }
            }
        }
        catch (SchedulerException e) {
            triggerDescriptor.setStatus("ERROR");
            e.printStackTrace();
        }
        return triggerDescriptor;
    }

    @Override
    public boolean removeTrigger(TriggerDescriptor triggerDescriptor) {
        TriggerKey triggerKey = triggerDescriptor.buildTrigger().getKey();
        try {
            return scheduler.unscheduleJob(triggerKey);
        }
        catch (SchedulerException e) {
            logger.error("SchedulerException while removing trigger. trigger key : {}, message : {}", triggerKey,
                    e.getLocalizedMessage(), e);
            return false;
        }
    }

    @Override
    public TriggerDescriptor getTrigger(String triggerName, String triggerGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        try {
            Trigger trigger = scheduler.getTrigger(triggerKey);
            return TriggerDescriptor.buildDescriptor(trigger).setTimeZone(TimeZone.getTimeZone(timezone));
        }
        catch (SchedulerException e) {
            logger.error("SchedulerException while getting trigger. trigger key : {}, message : {}", triggerKey,
                    e.getLocalizedMessage(), e);
            return null;
        }
    }

    @Override
    public boolean pauseTrigger(String triggerName, String triggerGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        try {
            scheduler.pauseTrigger(triggerKey);
            logger.info("Paused trigger with key - {}", triggerKey);
            return true;
        }
        catch (SchedulerException e) {
            logger.error("Could not pause trigger with key - {} due to error - {}", triggerKey,
                    e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean resumeTrigger(String triggerName, String triggerGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        try {
            scheduler.resumeTrigger(triggerKey);
            logger.info("Resumed trigger with key - {}", triggerKey);
            return true;
        }
        catch (SchedulerException e) {
            logger.error("Could not resume trigger with key - {} due to error - {}", triggerKey,
                    e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public TriggerDescriptor transferCronDetailsToCron(TriggerDescriptor triggerDescriptor) {
        CronDetails cronDetails = triggerDescriptor.getCronDetails();
        Integer seconds = (cronDetails.getSeconds() != null) ? cronDetails.getSeconds() : 0;
        Integer minites = (cronDetails.getMinutes() != null) ? cronDetails.getMinutes() : 0;
        Integer hours = (cronDetails.getHours() != null) ? cronDetails.getHours() : 0;
        String dayofMonth = ((cronDetails.getDayofMonth() != null) && (cronDetails.getDayofMonth() != ""))
                ? cronDetails.getDayofMonth()
                : "?";
        String mouth = ((cronDetails.getMonth() != null) && (cronDetails.getMonth() != "")) ? cronDetails.getMonth()
                : "*";
        String dayOfTheWeek = ((cronDetails.getDayOfWeek() != null) && (cronDetails.getDayOfWeek() != ""))
                ? cronDetails.getDayOfWeek()
                : "?";
        String year = ((cronDetails.getYear() != null) && (cronDetails.getYear() != "")) ? " " + cronDetails.getYear()
                : "";
        if (dayofMonth.equals("?") && dayOfTheWeek.equals("?")) {
            dayofMonth = "*";
        }
        if ((seconds != null) || (minites != null) || (hours != null) || (dayofMonth != null) || (mouth != null)
                || (dayOfTheWeek != null) || (year != null)) {
            triggerDescriptor.setCronExpression(
                    seconds + " " + minites + " " + hours + " " + dayofMonth + " " + mouth + " " + dayOfTheWeek + year);
        }
        return triggerDescriptor;
    }

    @Override
    public TriggerDescriptor transferCronToCronDetails(TriggerDescriptor triggerDescriptor) {
        String cronExpression = triggerDescriptor.getCronExpression();

        String[] cronStringArray = cronExpression.trim().split(" ");
        List<String> cronList = Arrays.asList(cronStringArray);
        String seconds = cronList.get(0);
        String minutes = cronList.get(1);
        String hours = cronList.get(2);
        String dayofMonth = cronList.get(3);
        String mouth = cronList.get(4);
        String dayOfWeek = cronList.get(5);
        if (dayOfWeek.equals("?")) {
            dayOfWeek = null;
        }
        // String year =cronList.get(0);
        CronDetails cronDetails = new CronDetails();
        cronDetails.setSeconds(Integer.parseInt(seconds));
        cronDetails.setMinutes(Integer.parseInt(minutes));
        cronDetails.setHours(Integer.parseInt(hours));
        cronDetails.setDayofMonth(dayofMonth);
        cronDetails.setMonth(mouth);
        cronDetails.setDayOfWeek(dayOfWeek);
        triggerDescriptor.setCronDetails(cronDetails);
        return triggerDescriptor;
    }

    @Override
    public TriggerDescriptor transferStartTimeDetailsToStartTime(TriggerDescriptor triggerDescriptor) {
        String startTimeTimestamp = triggerDescriptor.getStartTimeTimestamp();
        Calendar startCal = Calendar.getInstance();
        startCal.setTimeInMillis(Long.valueOf(startTimeTimestamp));
        triggerDescriptor.setStartTime(startCal.getTime());
        if (triggerDescriptor.getRepeatCount() == null) {
            triggerDescriptor.setRepeatCount(0);
        }
        if (triggerDescriptor.getRepeatInterval() == null) {
            triggerDescriptor.setRepeatInterval((long) 0);
        }
        return triggerDescriptor;
    }

    @Override
    public TriggerDescriptor transferStartTimeToStartTimeDetails(TriggerDescriptor triggerDescriptor) {
        Date startTime = triggerDescriptor.getStartTime();
        long timestamp = startTime.getTime();
        triggerDescriptor.setStartTimeTimestamp(String.valueOf(timestamp));
        return triggerDescriptor;
    }
}
