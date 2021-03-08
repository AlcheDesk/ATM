package com.meowlomo.atm.core.service.util.impl;

import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.service.util.UriInfoToStringUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UriInfoToStringUtilServiceImpl implements UriInfoToStringUtilService {

    @Override
    public String getMultivaluedMapToString(MultivaluedMap<String, String> multivaluedMap) {
        Set<String> keySet = multivaluedMap.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyArray.length; i++) {
            if ((String.valueOf(multivaluedMap.get(keyArray[i]))).trim().length() > 0) {
                sb.append(keyArray[i]).append("=")
                        .append(String.valueOf(multivaluedMap.get(keyArray[i]).get(0)).trim());
            }
            if (i != keyArray.length - 1) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    @Override
    public String getUriInfoToString(UriInfo uriInfo) {
        MultivaluedMap<String, String> multivaluedMap = uriInfo.getQueryParameters();
        String result = "";
        String pathString = uriInfo.getPath().toString();
        if ((pathString != null) && (!pathString.isEmpty())) {
            result = pathString;
        }
        String queryString = this.getMultivaluedMapToString(multivaluedMap);
        if ((queryString != null) && (!queryString.isEmpty())) {
            result = result + "?" + queryString;
        }
        return result;
    }
}
