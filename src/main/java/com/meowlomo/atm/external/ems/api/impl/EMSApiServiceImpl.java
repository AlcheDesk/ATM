package com.meowlomo.atm.external.ems.api.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.external.ems.api.EMSApiService;
import com.meowlomo.atm.external.ems.model.Job;
import com.meowlomo.atm.external.ems.model.Task;

@Service
public class EMSApiServiceImpl implements EMSApiService {

    private final Logger logger = LoggerFactory.getLogger(EMSApiServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public MeowlomoResponse sendTaskToEMS(List<Task> tasks) {
        String emsBaseUrl = RuntimeVariables.getProperty().get("meowlomo.atm.external.ems.base-url");
        String apiUrl = "api/tasks";
        try {
            URI uri = new URI(StringUtils.removeEnd(emsBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.postForObject(uri, tasks, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", emsBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", emsBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse sendJobsToEMS(List<Job> jobs) {
        String emsBaseUrl = RuntimeVariables.getProperty().get("meowlomo.atm.external.ems.base-url");
        String apiUrl = "api/jobs";
        try {
            URI uri = new URI(StringUtils.removeEnd(emsBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.postForObject(uri, jobs, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", emsBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", emsBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse getWorkerFromManager(String uuid) {
        String emsBaseUrl = RuntimeVariables.getProperty().get("meowlomo.atm.external.ems.base-url");
        String apiUrl = "api/workers/" + uuid;
        try {
            URI uri = new URI(StringUtils.removeEnd(emsBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.getForObject(uri, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", emsBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", emsBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse updateTaskToManager(String uuid, Task task) {
        String emsBaseUrl = RuntimeVariables.getProperty().get("meowlomo.atm.external.ems.base-url");
        String apiUrl = "api/tasks/" + uuid;
        try {
            URI uri = new URI(StringUtils.removeEnd(emsBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.patchForObject(uri, task, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", emsBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", emsBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse getGroups() {
        String emsBaseUrl = RuntimeVariables.getProperty().get("meowlomo.atm.external.ems.base-url");
        String apiUrl = "api/groups";
        try {
            URI uri = new URI(StringUtils.removeEnd(emsBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.getForObject(uri, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", emsBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", emsBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse getStatus() {
        String emsBaseUrl = RuntimeVariables.getProperty().get("meowlomo.atm.external.ems.base-url");
        String apiUrl = "api/statuses";
        try {
            URI uri = new URI(StringUtils.removeEnd(emsBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.getForObject(uri, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", emsBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", emsBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse getOperatingSystems() {
        String emsBaseUrl = RuntimeVariables.getProperty().get("meowlomo.atm.external.ems.base-url");
        String apiUrl = "api/operatingSystems";
        try {
            URI uri = new URI(StringUtils.removeEnd(emsBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.getForObject(uri, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", emsBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", emsBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse getJobTypes() {
        String emsBaseUrl = RuntimeVariables.getProperty().get("meowlomo.atm.external.ems.base-url");
        String apiUrl = "api/jobTypes";
        try {
            URI uri = new URI(StringUtils.removeEnd(emsBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.getForObject(uri, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", emsBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", emsBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse getTaskTypes() {
        String emsBaseUrl = RuntimeVariables.getProperty().get("meowlomo.atm.external.ems.base-url");
        String apiUrl = "api/taskTypes";
        try {
            URI uri = new URI(StringUtils.removeEnd(emsBaseUrl, "/") + "/" + apiUrl);
            return restTemplate.getForObject(uri, MeowlomoResponse.class);
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", emsBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", emsBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse terminateJobByUuid(String uuid) {
        String emsBaseUrl = RuntimeVariables.getProperty().get("meowlomo.atm.external.ems.base-url");
        String apiUrl = "api/utils/jobs/termination/" + uuid;
        try {
            URI uri = new URI(StringUtils.removeEnd(emsBaseUrl, "/") + "/" + apiUrl);
            ResponseEntity<MeowlomoResponse> response = restTemplate.exchange(uri, HttpMethod.DELETE, null,
                    MeowlomoResponse.class);
            return response.getBody();
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", emsBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", emsBaseUrl, apiUrl);
        }
        return null;
    }

    @Override
    public MeowlomoResponse terminateTaskByUuid(String uuid) {
        String emsBaseUrl = RuntimeVariables.getProperty().get("meowlomo.atm.external.ems.base-url");
        String apiUrl = "api/utils/tasks/termination/" + uuid;
        try {
            URI uri = new URI(StringUtils.removeEnd(emsBaseUrl, "/") + "/" + apiUrl);
            ResponseEntity<MeowlomoResponse> response = restTemplate.exchange(uri, HttpMethod.DELETE, null,
                    MeowlomoResponse.class);
            return response.getBody();
        }
        catch (URISyntaxException e) {
            logger.error("url error from base url :[{}] api url : [{}]", emsBaseUrl, apiUrl, e);
        }
        catch (ResourceAccessException e) {
            logger.error("access timeouted :[{}] api url : [{}]", emsBaseUrl, apiUrl);
        }
        return null;
    }

}
