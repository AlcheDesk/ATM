package com.meowlomo.atm.cache;

import java.io.Serializable;
import java.util.Base64;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meowlomo.atm.core.service.multitenancy.MultitenancyUtilService;

@Component
public class CacheKeyGenerator {

    private final Logger logger = LoggerFactory.getLogger(CacheKeyGenerator.class);
    
    @Autowired
    private MultitenancyUtilService multitenancyUtilService;
    
    private static String delimiter = "-";
    
    public String generateKey(Class<?> modelClass, Object idOrExample, Object... objects) {
        //get the modelClass name first
        Long tenantId = multitenancyUtilService.getCurrentTenantId();
        String modelClassKey = null;
        String idOrExampleKey = null;
        String objectString = "";
        if (modelClass != null) {
            modelClassKey = modelClass.getSimpleName();
        }
        //check the id or example object
        if (idOrExample != null && idOrExample instanceof Long) {
            idOrExampleKey += ((Long)idOrExample).toString();
        }
        else if (idOrExample != null && idOrExample instanceof Serializable && idOrExample.getClass().getSimpleName().toLowerCase().endsWith("example")) {
            String objectKeyString = Base64.getEncoder().encodeToString(SerializationUtils.serialize((Serializable) idOrExample));
             idOrExampleKey += objectKeyString;
        }
        // loop through the object array
        for(Object obj : objects) {
            if (obj != null){
                if (obj instanceof RowBounds) {
                    objectString += "_LIMIT"+((RowBounds) obj).getLimit() + "_OFFSET" + ((RowBounds) obj).getOffset();
                }
                else if (obj instanceof String) {
                    objectString += "_" + obj;
                }
                else {
                    objectString += "_" + obj.toString();
                }
            }
        }
        boolean errorDetected = false;
        if (tenantId == null) {
            logger.info("tenant id is not found in from current session, will not be used in cache key");
            
        }
        if (modelClassKey == null) {
            logger.error("model Key generated is null for class {}", modelClass.getSimpleName());
            errorDetected = true;  
        }
        
        // return value
        if (errorDetected) {
            return null;
        }
        if (tenantId == null) {
            String key = String.join(delimiter, modelClassKey, idOrExampleKey, objectString);
            logger.debug("redis Key calss name {} idexampleKey {}, objectString", modelClassKey, idOrExampleKey, objectString);
            logger.debug("redis Key generated {}", key);
            return key;
        }
        else {
            String key =  String.join(delimiter, tenantId.toString(), modelClassKey, idOrExampleKey, objectString);
            logger.debug("redis Key calss name {} idexampleKey {}, objectString", modelClassKey, idOrExampleKey, objectString);
            logger.debug("redis Key generated {}", key);
            return key;
        }        
    }
    
    public String generateClassKey(Class<?> modelClass) {
        //get the modelClass name first
        Long tenantId = multitenancyUtilService.getCurrentTenantId();
        String modelClassKey = null;
        if (modelClass != null) {
            modelClassKey = modelClass.getSimpleName();
        }
 

        boolean errorDetected = false;
        if (tenantId == null) {
            logger.info("tenant id is not found in from current session, will not be used in cache key");
            
        }
        if (modelClassKey == null) {
            errorDetected = true;  
        }
        
        // return value
        if (errorDetected) {
            return null;
        }
        if (tenantId == null) {
            return String.join(delimiter, modelClassKey, "*");
        }
        else {
            return String.join(delimiter, tenantId.toString(), modelClassKey, "*");
        }        
    }
}
