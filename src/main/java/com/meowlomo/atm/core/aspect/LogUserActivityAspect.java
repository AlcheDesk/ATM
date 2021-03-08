package com.meowlomo.atm.core.aspect;

import java.util.Arrays;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.meowlomo.atm.core.model.UserActivityLog;
import com.meowlomo.atm.core.service.base.UserActivityLogService;
import com.meowlomo.atm.security.model.AuthenticatedUserDetails;

@Aspect
@Component
public class LogUserActivityAspect {
    
    private final Logger logger = LoggerFactory.getLogger(LogUserActivityAspect.class);
    
    @Autowired
    private UserActivityLogService userActivityLogService;
    
    @Value("${meowlomo.atm.user-activity-log.internal-user-uuid:00112233-4455-6677-8899-aabbccddeeff}")
    private String INTERANL_USER_UUID;
    
    @Around("@annotation(com.meowlomo.atm.core.annotation.LogUserActivity)")
    public Object logUserActivity(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userUuid = null;
        String userName = "N/A";
        logger.debug("AOP caught authnetication class name {}", authentication.getClass().getName());
        if ((authentication instanceof AbstractAuthenticationToken)) {
            //get the user details
            Object userDetails = authentication.getDetails();
            logger.debug("AOP caught user details class name {}", userDetails.getClass().getName());
            if (userDetails instanceof AuthenticatedUserDetails) {
                userUuid = ((AuthenticatedUserDetails) userDetails).getUuid();
                userName = ((AuthenticatedUserDetails) userDetails).getUsername() == null ? userName : ((AuthenticatedUserDetails) userDetails).getUsername();
                logger.debug("AOP caught user uuid {}", userUuid.toString());
            }
            else {
                userUuid = UUID.fromString(INTERANL_USER_UUID);
            }
        }
        else {
            userUuid = UUID.fromString(INTERANL_USER_UUID);
        }
        
        //special process now, direct return for internal user
        if (userUuid.equals(UUID.fromString(INTERANL_USER_UUID))) {
            return proceedingJoinPoint.proceed();
        }
        
        Signature signature = proceedingJoinPoint.getSignature();
        // class name
        String className = signature.getDeclaringTypeName();
        className = className.substring(className.lastIndexOf(".") + 1);
        // get the method name first 
        String methodName = signature.getName();
        // get parameters
        MethodSignature methodSignature = (MethodSignature)signature;
        @SuppressWarnings("rawtypes")
        Class[] parameterNames = methodSignature.getParameterTypes();
        Object[] parameterValues = proceedingJoinPoint.getArgs();

        
        // parameters need to save to user activity log
        String input = null;
        String targetModel = null;
        String actionName = methodName;
        
        String parameterString = null;
        if (parameterNames.length != 0) {
            StringBuilder stringBuilder = new StringBuilder();            
            for (int i = 0 ; i < parameterNames.length; i++) {
                String fullParameterClassName = parameterNames[i].getName();
                // check object from this project
                if (fullParameterClassName.startsWith("com.meowlomo")) {
                    stringBuilder.append("["+parameterNames[i].getSimpleName()+"=>"+parameterValues[i].toString()+"]");
                }
                // check array of objects from this project
                else if (fullParameterClassName.startsWith("[Lcom.meowlomo")){
                    stringBuilder.append("["+parameterNames[i].getSimpleName()+"=>"+Arrays.toString((Object[])parameterValues[i])+"]");
                }
                else if (fullParameterClassName.startsWith("javax.servlet.http.HttpServletRequest")) {
                    HttpServletRequest httpServletRequest = (HttpServletRequest) parameterValues[i];
                    targetModel = httpServletRequest.getPathInfo();
                }
            }
            parameterString = stringBuilder.toString();
        }
        // build the action log
        input = parameterString == null ? "" : parameterString;
        Object process =  proceedingJoinPoint.proceed();
        
        //add the user activity log
        UserActivityLog userActivityLog = new UserActivityLog();
        userActivityLog.setActivityUuid(UUID.randomUUID());
        userActivityLog.setTargetModel(targetModel);
        userActivityLog.setActionName(actionName);
        userActivityLog.setInput(input);
        userActivityLog.setUserUuid(userUuid);
        userActivityLog.setUserName(userName);
        userActivityLogService.insertSelective(userActivityLog);
        return process;
    }

}
