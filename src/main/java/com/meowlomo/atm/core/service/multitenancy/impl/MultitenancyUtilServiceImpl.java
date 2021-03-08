package com.meowlomo.atm.core.service.multitenancy.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.meowlomo.atm.core.service.multitenancy.MultitenancyUtilService;
import com.meowlomo.atm.security.model.AuthenticatedUserDetails;

@Service
public class MultitenancyUtilServiceImpl implements MultitenancyUtilService{

    private final Logger logger = LoggerFactory.getLogger(MultitenancyUtilServiceImpl.class);
    
    
    @Value("${meowlomo.security.authentication.header.name:Authorization}")
    private String authenticationHeaderName;
    
    @Value("${meowlomo.security.jwt.header.prefix:'Bearer '}")
    private String jwtTokenPrefix;
    
    @Autowired
    private HttpServletRequest httpServletRequest;
    
    @Override
    public Long getCurrentTenantId() {
        Long tenantId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            logger.error("Not authenticated user info avaliable, please contact system admin. default user id will be used.");
            return Long.valueOf(1000);
        }        
        else if ((authentication instanceof AbstractAuthenticationToken)) {
            //get the user details
            Object userDetails = authentication.getDetails();
            logger.debug("multitenancy caught user details class name {}", userDetails.getClass().getName());
            if (userDetails instanceof AuthenticatedUserDetails) {
                tenantId = ((AuthenticatedUserDetails) userDetails).getTenantId();
                logger.debug("multitenancy caught user id {}", tenantId);
                return tenantId;
            }
            else {
                logger.error("authenticated user has no id info avaliable, please contact system admin. default user id will be used.");
                return Long.valueOf(1000);
            }
        }
        else {
            tenantId = ((AuthenticatedUserDetails) authentication.getPrincipal()).getTenantId(); 
            logger.debug("Get current tenant id {} for other services use", tenantId);
            if (tenantId == null) {
                logger.warn("Get current tenant id is NULL, default tenant id 1000 will be used", tenantId);
                tenantId = Long.valueOf(1000);
            }
            return tenantId;
        }
    }

    @Override
    public String getAuthnticationToken() {
        String tokenString = httpServletRequest.getHeader(authenticationHeaderName);
        logger.debug("Multitenancy service caught request header Authorization value {}", tokenString);
        if (StringUtils.hasText(tokenString) && tokenString.startsWith(jwtTokenPrefix)) {
            return tokenString.substring(jwtTokenPrefix.length(), tokenString.length());
        }
        return null;
    }
}
