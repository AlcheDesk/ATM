package com.meowlomo.atm.external.ems.api;

import java.util.List;

import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.external.ems.model.Job;
import com.meowlomo.atm.external.ems.model.Task;

public interface EMSApiService {

	MeowlomoResponse sendTaskToEMS(List<Task> tasks);

	MeowlomoResponse sendJobsToEMS(List<Job> jobs);

	MeowlomoResponse getWorkerFromManager(String uuid);

	MeowlomoResponse updateTaskToManager(String uuid, Task task);

	MeowlomoResponse getGroups();

	MeowlomoResponse getStatus();

	MeowlomoResponse getOperatingSystems();

	MeowlomoResponse getJobTypes();

	MeowlomoResponse getTaskTypes();

	MeowlomoResponse terminateJobByUuid(String jobUuidString);

	MeowlomoResponse terminateTaskByUuid(String taskUuidString);
}