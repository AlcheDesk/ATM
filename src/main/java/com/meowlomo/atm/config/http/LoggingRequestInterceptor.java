package com.meowlomo.atm.config.http;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

	private final Logger logger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		traceRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		traceResponse(response);
		return response;
	}

	private void traceRequest(HttpRequest request, byte[] body) throws IOException {
		logger.info("===========================request begin================================================");
		logger.info("URI         : {}", request.getURI());
		logger.debug("Method      : {}", request.getMethod());
		logger.debug("Headers     : {}", request.getHeaders());
		logger.debug("Request body: {}", new String(body, "UTF-8"));
		logger.info("==========================request end================================================");
	}

	private void traceResponse(ClientHttpResponse response) throws IOException {
		logger.info("============================response begin==========================================");
		logger.info("Status code  : {}", response.getStatusCode());
		logger.debug("Status text  : {}", response.getStatusText());
		logger.debug("Headers      : {}", response.getHeaders());
		logger.debug("Response body: {}", IOUtils.toString(response.getBody(), "UTF-8"));
		logger.info("=======================response end=================================================");
	}

}
