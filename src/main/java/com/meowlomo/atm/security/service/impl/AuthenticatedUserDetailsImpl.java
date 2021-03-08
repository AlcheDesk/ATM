package com.meowlomo.atm.security.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.meowlomo.atm.security.model.AuthenticatedUserDetails;
import com.meowlomo.atm.security.service.AuthenticatedUserService;

@Service
public class AuthenticatedUserDetailsImpl implements AuthenticatedUserService{

    private final Logger logger = LoggerFactory.getLogger(AuthenticatedUserDetailsImpl.class);
    
    @Value("${meowlomo.security.authentication.administration.uuid:00000000-0000-0000-0000-000000000000}")
    private String predefindedMeowlomoAdminUuidString;   
    @Value("${meowlomo.security.authentication.administration.account-uuid:00000000-0000-0000-0000-000000000000}")
    private String predefindedMeowlomoAdminAccountUuidString;
    @Value("${meowlomo.security.authentication.administration.username:meowlomo}")
    private String predefindedMeowlomoAdminUsername;
    
    @Override
    public AuthenticatedUserDetails getAuthenticateUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("Got request authnetication class name {}", authentication.getClass().getName());
        if ((authentication instanceof AbstractAuthenticationToken)) {
            //get the user details
            Object userDetails = authentication.getDetails();
            logger.debug("AOP caught user details class name {}", userDetails.getClass().getName());
            if (userDetails instanceof AuthenticatedUserDetails) {
                return (AuthenticatedUserDetails) userDetails;
            }
            else {
                logger.debug("the user details is not a valid request user detail object, the object name is {}", userDetails.getClass().getName());
                return null;
            }
        }
        else {
            logger.debug("the request authnetication is not a valid AbstractAuthenticationToken object, the object name is {}", authentication.getClass().getName());
            return null;
        }
    }
    
    @Override
    public Boolean isMeowlomoAdministrator(AuthenticatedUserDetails authenticatedUser) {
        if (authenticatedUser.getUuid() == null || 
                authenticatedUser.getUsername() == null || 
                authenticatedUser.getAccountUuids() == null ||
                authenticatedUser.getAccountUuids().isEmpty()) {
            return false;
        }
        else {
            UUID predefinedMLAdminAccountUuid = UUID.fromString(this.predefindedMeowlomoAdminAccountUuidString);
            UUID predefinedMLAdminUserUuid = UUID.fromString(this.predefindedMeowlomoAdminUuidString);
            if (authenticatedUser.getAccountUuids().contains(predefinedMLAdminAccountUuid) && 
                    predefinedMLAdminUserUuid.equals(authenticatedUser.getUuid()) && 
                predefindedMeowlomoAdminUsername.equals(authenticatedUser.getUsername())) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}
