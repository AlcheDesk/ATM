//package com.meowlomo.atm.core.resource.exception.mapper;
//
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.ExceptionMapper;
//import javax.ws.rs.ext.Provider;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.meowlomo.atm.core.resource.exception.CustomInvalidAuthenticationTokenException;
//import com.meowlomo.atm.core.resource.model.MeowlomoErrorResponse;
//import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
//
//@Component
//@Provider
//public class CustomInvalidAuthenticationTokenExceptionMapper implements ExceptionMapper<CustomInvalidAuthenticationTokenException> {
//
//    private final Logger logger = LoggerFactory.getLogger(CustomInvalidAuthenticationTokenExceptionMapper.class);
//    
//    @Override
//    public Response toResponse(CustomInvalidAuthenticationTokenException exception) {
//
//        MeowlomoErrorResponse restErrorResponse = new MeowlomoErrorResponse(exception.getStatus(),
//                // type
//                exception.getType(),
//                // message
//                exception.getMessage(),
//                // developer message
//                exception.getDeveloperMessage(), exception.getCode(), exception.getUuid());
//
//        MeowlomoResponse response = new MeowlomoResponse(null, null, restErrorResponse);
//
//        logger.error(response.toString());
//
//        return Response.status(restErrorResponse.getStatusCode()).entity(response).type(MediaType.APPLICATION_JSON)
//                .build();
//    }
//
//}
