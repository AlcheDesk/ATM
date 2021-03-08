package com.meowlomo.atm.core.service.util;

import com.meowlomo.atm.core.model.DriverPackDriverLink;

public interface DriverPackDriverLinkUtilService {
    boolean isIgnoredWebBrowserLink(DriverPackDriverLink record);
    void removeInvalidLinks();
}
