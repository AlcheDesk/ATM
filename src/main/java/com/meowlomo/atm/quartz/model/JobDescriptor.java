package com.meowlomo.atm.quartz.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobDescriptor {
	
	private String name;
	private String group;
	private Map<String, Object> data = new LinkedHashMap<>();
	@JsonProperty("triggers")
	private List<TriggerDescriptor> triggerDescriptors = new ArrayList<TriggerDescriptor>();

	public JobDescriptor setName(final String name) {
		this.name = name;
		return this;
	}

	public JobDescriptor setGroup(final String group) {
		this.group = group;
		return this;
	}

	public JobDescriptor setData(final Map<String, Object> data) {
		this.data = data;
		return this;
	}

	public JobDescriptor setTriggerDescriptors(final List<TriggerDescriptor> triggerDescriptors) {
		this.triggerDescriptors = triggerDescriptors;
		return this;
	}

	public String getName() {
		return name;
	}

	public String getGroup() {
		return group;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public List<TriggerDescriptor> getTriggerDescriptors() {
		return triggerDescriptors;
	}

	/**
	 * Convenience method for building Triggers of Job
	 * 
	 * @return Triggers for this JobDetail
	 */
	@JsonIgnore
	public Set<Trigger> buildTriggers() {
		Set<Trigger> triggers = new LinkedHashSet<>();
		for (TriggerDescriptor triggerDescriptor : triggerDescriptors) {
			triggers.add(triggerDescriptor.buildTrigger());
		}
		return triggers;
	}

	/**
	 * Convenience method that builds a JobDetail
	 * 
	 * @return the JobDetail built from this descriptor
	 */
	@JsonIgnore
	public JobDetail buildJobDetail(Class <? extends Job> jobClass) {
		// @formatter:off
		return JobBuilder.newJob(jobClass)
                .withIdentity(name, group)
                .usingJobData(new JobDataMap(data))
                .build();
		// @formatter:on
	}

	/**
	 * Convenience method that builds a descriptor from JobDetail and Trigger(s)
	 * 
	 * @param jobDetail
	 *            the JobDetail instance
	 * @param triggersOfJob
	 *            the Trigger(s) to associate with the Job
	 * @return the JobDescriptor
	 */
	public static JobDescriptor buildDescriptor(JobDetail jobDetail, List<? extends Trigger> triggersOfJob) {
		// @formatter:off
		List<TriggerDescriptor> triggerDescriptors = new ArrayList<TriggerDescriptor>();

		for (Trigger trigger : triggersOfJob) {
		    triggerDescriptors.add(TriggerDescriptor.buildDescriptor(trigger));
		}
		
		return new JobDescriptor()
				.setName(jobDetail.getKey().getName())
				.setGroup(jobDetail.getKey().getGroup())
				.setData(jobDetail.getJobDataMap().getWrappedMap())
				.setTriggerDescriptors(triggerDescriptors);
		// @formatter:on
	}
}
