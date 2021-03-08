package com.meowlomo.atm.config.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import com.meowlomo.atm.core.resource.exception.CustomBadRequestException;
import com.meowlomo.atm.core.resource.exception.CustomInternalServerErrorException;
import com.meowlomo.atm.core.resource.exception.CustomNotAcceptableException;
import com.meowlomo.atm.core.resource.exception.CustomNotAllowedException;
import com.meowlomo.atm.core.resource.exception.CustomNotAuthorizedException;
import com.meowlomo.atm.core.resource.exception.CustomNotFoundException;
import com.meowlomo.atm.core.resource.exception.CustomNotSupportedException;
import com.meowlomo.atm.core.resource.exception.CustomServiceUnavailableException;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateErrorHandler.class);
	private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		StringBuilder inputStringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(response.getBody(), "UTF-8"));
		String line = bufferedReader.readLine();
		while (line != null) {
			inputStringBuilder.append(line);
			inputStringBuilder.append('\n');
			line = bufferedReader.readLine();
		}
		HttpStatus statusCode = response.getStatusCode();
		String statusText = response.getStatusText();
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			logger.debug(HttpStatus.BAD_REQUEST + " response. Throwing BadRequestException");
			NotAuthorizedException ex = new NotAuthorizedException("request is not authorized by external api");
			throw new CustomBadRequestException(ex, statusText, ExceptionUtils.getStackTrace(ex), statusCode.name(),
					UUID.randomUUID());
		} else if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
			logger.debug(HttpStatus.FORBIDDEN + " response. Throwing ForbiddenException");
			NotAuthorizedException ex = new NotAuthorizedException("request is forbidden by external api");
			throw new CustomNotAcceptableException(ex, statusText, ExceptionUtils.getStackTrace(ex), statusCode.name(),
					UUID.randomUUID());
		} else if (response.getStatusCode() == HttpStatus.NOT_ACCEPTABLE) {
			logger.debug(HttpStatus.NOT_ACCEPTABLE + " response. Throwing NotAcceptableException");
			NotAuthorizedException ex = new NotAuthorizedException("request is not acceptable by external api");
			throw new CustomNotAuthorizedException(ex, statusText, ExceptionUtils.getStackTrace(ex), statusCode.name(),
					UUID.randomUUID());
		} else if (response.getStatusCode() == HttpStatus.METHOD_NOT_ALLOWED) {
			logger.debug(HttpStatus.METHOD_NOT_ALLOWED + " response. Throwing NotAllowedException");
			NotAuthorizedException ex = new NotAuthorizedException("request method is not allowed by external api");
			throw new CustomNotAllowedException(ex, statusText, ExceptionUtils.getStackTrace(ex), statusCode.name(),
					UUID.randomUUID());
		} else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			logger.debug(HttpStatus.UNAUTHORIZED + " response. Throwing NotAuthorizedException");
			NotAuthorizedException ex = new NotAuthorizedException("request is not authorized by external api");
			throw new CustomNotAuthorizedException(ex, statusText, ExceptionUtils.getStackTrace(ex), statusCode.name(),
					UUID.randomUUID());
		} else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			logger.debug(HttpStatus.NOT_FOUND + " response. Throwing NotFoundException");
			NotAuthorizedException ex = new NotAuthorizedException("request target is not found by external api");
			throw new CustomNotFoundException(ex, statusText, ExceptionUtils.getStackTrace(ex), statusCode.name(),
					UUID.randomUUID());
		} else if (response.getStatusCode() == HttpStatus.UNSUPPORTED_MEDIA_TYPE) {
			logger.debug(HttpStatus.UNSUPPORTED_MEDIA_TYPE + " response. Throwing NotSupportedException");
			NotAuthorizedException ex = new NotAuthorizedException(
					"request media type is not supported by external api");
			throw new CustomNotSupportedException(ex, statusText, ExceptionUtils.getStackTrace(ex), statusCode.name(),
					UUID.randomUUID());
		} else if (response.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
			logger.debug(HttpStatus.SERVICE_UNAVAILABLE + " response. Throwing ServiceUnavailableException");
			NotAuthorizedException ex = new NotAuthorizedException("service is unavaliable by external api");
			throw new CustomServiceUnavailableException(ex, statusText, ExceptionUtils.getStackTrace(ex),
					statusCode.name(), UUID.randomUUID());
		} else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
			logger.debug(HttpStatus.INTERNAL_SERVER_ERROR + " response. Throwing InternalServerErrorException");
			NotAuthorizedException ex = new NotAuthorizedException("external api responsed internal server error");
			throw new CustomInternalServerErrorException(ex, statusText, ExceptionUtils.getStackTrace(ex),
					statusCode.name(), UUID.randomUUID());
		} else {
			logger.debug(HttpStatus.INTERNAL_SERVER_ERROR + " response. Throwing authentication exception");
			InternalServerErrorException ex = new InternalServerErrorException(
					"error on handling error from external api");
			throw new CustomInternalServerErrorException(ex, statusText, ExceptionUtils.getStackTrace(ex),
					statusCode.name(), UUID.randomUUID());
		}
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode() != HttpStatus.OK) {
			logger.debug("===========================response error begin================================================");
			logger.debug("Status code  : {}", response.getStatusCode());
			logger.debug("Status text  : {}", response.getStatusText());
			logger.debug("Headers      : {}", response.getHeaders());
			logger.debug("Response body: {}", IOUtils.toString(response.getBody(), "UTF-8"));
			logger.debug("==========================response error end================================================");
		}
		return errorHandler.hasError(response);
	}
}