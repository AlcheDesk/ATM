package com.meowlomo.atm.quartz.service;

import java.util.List;

import com.meowlomo.atm.quartz.model.JobDescriptor;
import com.meowlomo.atm.quartz.model.TriggerDescriptor;

public interface TriggerService {

    TriggerDescriptor updateTrigger(String triggerName, String triggerGroup, TriggerDescriptor newTrigger);

    TriggerDescriptor addTrigger(JobDescriptor jobDescriptor, TriggerDescriptor triggerDescriptor);

    TriggerDescriptor getTrigger(String triggerName, String triggerGroup);

    boolean removeTriggers(List<TriggerDescriptor> triggerDescriptors);

    boolean removeTrigger(TriggerDescriptor triggerDescriptor);

    List<TriggerDescriptor> setTriggersStatus(List<TriggerDescriptor> triggerDescriptors);

    boolean pauseTrigger(String triggerName, String triggerGroup);

    boolean resumeTrigger(String triggerName, String triggerGroup);

    TriggerDescriptor transferCronDetailsToCron(TriggerDescriptor triggerDescriptors);

    TriggerDescriptor transferCronToCronDetails(TriggerDescriptor triggerDescriptors);

    TriggerDescriptor transferStartTimeDetailsToStartTime(TriggerDescriptor triggerDescriptors);

    TriggerDescriptor transferStartTimeToStartTimeDetails(TriggerDescriptor triggerDescriptors);
}
