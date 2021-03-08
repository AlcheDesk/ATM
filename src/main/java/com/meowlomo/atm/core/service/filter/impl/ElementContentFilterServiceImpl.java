package com.meowlomo.atm.core.service.filter.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.atm.core.model.Element;
import com.meowlomo.atm.core.service.filter.ElementContentFilterService;
import com.meowlomo.atm.core.service.util.CommonUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ElementContentFilterServiceImpl implements ElementContentFilterService {

    @Autowired
    private CommonUtil commonUtilService;
    
    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(ElementContentFilterServiceImpl.class);

    @Override
    public Element convertParameterJsonNamingConvention(Element record) {
        // check is there is a parameter field
        if (record.getParameter() != null) {
            // get the parameter
            JsonNode parameter = record.getParameter();
            JsonNode formatedParameter = commonUtilService.convertNameToCamelCase(parameter);
            record.setParameter(formatedParameter);
        }
        return record;
    }

    @Override
    public Element generateAPIDetailsFromURl(Element record) {
        if (record.getParameter() != null && record.getParameter().has("url")) {
            // get the parameter
            JsonNode parameter = record.getParameter();
            // get the url
            String urlString = parameter.get("url").asText();

            UrlValidator urlValidator = new UrlValidator();
            if (urlValidator.isValid(urlString)) {
                URL url = null;
                try {
                    url = new URL(urlString);
                    String protocol = url.getProtocol();
                    String host = url.getHost();
                    int port = url.getPort();
                    String baseUrl = url.getPath();
                    ((ObjectNode)parameter).put("protocol", protocol);
                    ((ObjectNode)parameter).put("host", host);
                    ((ObjectNode)parameter).put("port", port);
                    ((ObjectNode)parameter).put("baseUrl", baseUrl);
                    record.setParameter(parameter);
                }
                catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else {
                //the url is invalid or null
                ((ObjectNode)parameter).putNull("protocol");
                ((ObjectNode)parameter).putNull("host");
                ((ObjectNode)parameter).putNull("port");
                ((ObjectNode)parameter).putNull("baseUrl");
                record.setParameter(parameter);
            }

        }
        return record;
    }

}
