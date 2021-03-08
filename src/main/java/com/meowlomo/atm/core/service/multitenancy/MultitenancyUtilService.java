package com.meowlomo.atm.core.service.multitenancy;

public interface MultitenancyUtilService {

    Long getCurrentTenantId();
    
    String getAuthnticationToken();
    
}
