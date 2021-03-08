package com.meowlomo.atm.core.service.util;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

public interface UriInfoToStringUtilService {
    public String getMultivaluedMapToString(MultivaluedMap<String, String> multivaluedMap);

    public String getUriInfoToString(UriInfo uriInfo);

}
