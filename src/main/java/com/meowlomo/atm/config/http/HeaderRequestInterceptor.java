package com.meowlomo.atm.config.http;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {

    private final Logger logger = LoggerFactory.getLogger(HeaderRequestInterceptor.class);
    
    private String authenticationHeaderName;

    @Autowired
    public HeaderRequestInterceptor(String authenticationHeaderName) {
        this.authenticationHeaderName = authenticationHeaderName;
    }
    
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return execution.execute(request, body);
        }
        HttpServletRequest httpServletRequest =  servletRequestAttributes.getRequest();
        String tokenString = httpServletRequest.getHeader(authenticationHeaderName);
        if (tokenString != null && !tokenString.isEmpty() && authenticationHeaderName != null && !authenticationHeaderName.isEmpty()) {
            logger.debug("Set header [{}] with value [{}] for external call", authenticationHeaderName, tokenString);
            request.getHeaders().set(authenticationHeaderName, tokenString);
        }
        else {
            logger.debug("Header info: header name => {}, header value => {} ", authenticationHeaderName, tokenString);
        }
        return execution.execute(request, body);
    }
}
