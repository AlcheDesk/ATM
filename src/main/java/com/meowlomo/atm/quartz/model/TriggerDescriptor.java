package com.meowlomo.atm.quartz.model;

import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import javax.validation.constraints.Future;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TriggerDescriptor {

    private String name;
    private String group;
    private String status;

    private String cronExpression;// cron
    private CronDetails cronDetails;

    @Future(message = "startTime需要是一个将来的时间")
    private Date startTime;// ccron, daily, simple
    private String startTimeTimestamp;

    @Future(message = "endTime需要是一个将来的时间")
    private Date endTime;// cron, daily, simple
    private Date previousFireTime; // cron, daily, simple
    private TimeZone timeZone;// cron, daily, simple
    private Date nextFireTime;
    private Integer repeatCount;
    private Long repeatInterval;
    private Integer timesTriggered;

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getPreviousFireTime() {
        return previousFireTime;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }

    public Integer getRepeatCount() {
        return repeatCount;
    }

    public Long getRepeatInterval() {
        return repeatInterval;
    }

    public Integer getTimesTriggered() {
        return timesTriggered;
    }

    public String getStatus() {
        return status;
    }

    public TriggerDescriptor setName(String name) {
        this.name = name;
        return this;
    }

    public TriggerDescriptor setGroup(String group) {
        this.group = group;
        return this;
    }

    public TriggerDescriptor setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
        return this;
    }

    public TriggerDescriptor setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public TriggerDescriptor setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public TriggerDescriptor setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public TriggerDescriptor setStatus(String status) {
        this.status = status;
        return this;
    }

    private TriggerDescriptor setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
        return this;
    }

    private TriggerDescriptor setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
        return this;
    }

    public TriggerDescriptor setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public TriggerDescriptor setRepeatInterval(Long repeatInterval) {
        this.repeatInterval = repeatInterval;
        return this;
    }

    private TriggerDescriptor setTimesTriggered(Integer timesTriggered) {
        this.timesTriggered = timesTriggered;
        return this;
    }

    public CronDetails getCronDetails() {
        return cronDetails;
    }

    public void setCronDetails(CronDetails cronDetails) {
        this.cronDetails = cronDetails;
    }

    public String getStartTimeTimestamp() {
        return startTimeTimestamp;
    }

    public void setStartTimeTimestamp(String startTimeTimestamp) {
        this.startTimeTimestamp = startTimeTimestamp;
    }

    private String buildName() {
        return StringUtils.isEmpty(name) ? UUID.randomUUID().toString() : name;
    }

    @JsonIgnore
    public Trigger buildTrigger() {
        // @formatter:off
        if (!StringUtils.isEmpty(cronExpression)) {
            if (!CronExpression.isValidExpression(cronExpression))
                throw new IllegalArgumentException(
                        "Provided expression '" + cronExpression + "' is not a valid cron expression");
            return TriggerBuilder.newTrigger().withIdentity(buildName(), group).withSchedule(CronScheduleBuilder
                    .cronSchedule(cronExpression).withMisfireHandlingInstructionFireAndProceed().inTimeZone(timeZone))
                    .build();
        }
        else if (!StringUtils
                .isEmpty(startTime)) { return TriggerBuilder.newTrigger().withIdentity(buildName(), group)
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .withMisfireHandlingInstructionNextWithExistingCount().withRepeatCount(repeatCount)
                                .withIntervalInMilliseconds(repeatInterval))
                        .startAt(startTime == null ? new Date() : startTime).endAt(endTime).build(); }
        // @formatter:on
        throw new IllegalStateException("Specify either one of 'cron' or 'fireTime'");
    }

    @JsonIgnore
    public static TriggerDescriptor buildDescriptor(Trigger trigger) {
        // @formatter:off
        TriggerDescriptor triggerDescriptor = new TriggerDescriptor().setName(trigger.getKey().getName())
                .setGroup(trigger.getKey().getGroup());
        if (trigger instanceof SimpleTrigger) {
            triggerDescriptor.setEndTime(((SimpleTrigger) trigger).getEndTime());
            triggerDescriptor.setNextFireTime(((SimpleTrigger) trigger).getNextFireTime());
            triggerDescriptor.setPreviousFireTime(((SimpleTrigger) trigger).getPreviousFireTime());
            triggerDescriptor.setRepeatCount(((SimpleTrigger) trigger).getRepeatCount());
            triggerDescriptor.setRepeatInterval(((SimpleTrigger) trigger).getRepeatInterval());
            triggerDescriptor.setTimesTriggered(((SimpleTrigger) trigger).getTimesTriggered());
            triggerDescriptor.setStartTime(((SimpleTrigger) trigger).getStartTime());
        }
        else if (trigger instanceof CronTrigger) {
            triggerDescriptor.setCronExpression(((CronTrigger) trigger).getCronExpression());
            triggerDescriptor.setEndTime(((CronTrigger) trigger).getEndTime());
            triggerDescriptor.setNextFireTime(((CronTrigger) trigger).getNextFireTime());
            triggerDescriptor.setPreviousFireTime(((CronTrigger) trigger).getPreviousFireTime());
            triggerDescriptor.setStartTime(((CronTrigger) trigger).getStartTime());
            triggerDescriptor.setTimeZone(((CronTrigger) trigger).getTimeZone());
        }
        return triggerDescriptor;
        // @formatter:on
    }

}
