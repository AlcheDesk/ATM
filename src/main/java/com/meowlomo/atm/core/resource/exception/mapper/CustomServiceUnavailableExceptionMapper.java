package com.meowlomo.atm.core.resource.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.meowlomo.atm.core.resource.exception.CustomServiceUnavailableException;
import com.meowlomo.atm.core.resource.model.MeowlomoErrorResponse;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;

@Component
@Provider
public class CustomServiceUnavailableExceptionMapper implements ExceptionMapper<CustomServiceUnavailableException> {

    private final Logger logger = LoggerFactory.getLogger(CustomServiceUnavailableExceptionMapper.class);

    @Override
    public Response toResponse(CustomServiceUnavailableException exception) {

        MeowlomoErrorResponse restErrorResponse = new MeowlomoErrorResponse(exception.getStatus(),
                // type
                exception.getType(),
                // message
                exception.getMessage(),
                // developer message
                exception.getDeveloperMessage(), exception.getCode(), exception.getUuid());

        MeowlomoResponse response = new MeowlomoResponse(null, null, restErrorResponse);

        logger.error(response.toString());

        return Response.status(restErrorResponse.getStatusCode()).entity(response).type(MediaType.APPLICATION_JSON)
                .build();
    }

}
