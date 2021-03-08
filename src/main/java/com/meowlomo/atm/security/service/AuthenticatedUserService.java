package com.meowlomo.atm.security.service;

import com.meowlomo.atm.security.model.AuthenticatedUserDetails;

public interface AuthenticatedUserService {
    AuthenticatedUserDetails getAuthenticateUserDetails();

    Boolean isMeowlomoAdministrator(AuthenticatedUserDetails authenticatedUser);
}
