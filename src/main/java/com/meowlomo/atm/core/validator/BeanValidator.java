package com.meowlomo.atm.core.validator;

import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.meowlomo.atm.core.resource.exception.CustomBadRequestException;

@Component
public class BeanValidator {

    private final static Logger logger = LoggerFactory.getLogger(BeanValidator.class);

    public static <T> void BeanValidation(T record, String ERROR_TYPE) {
        logger.info("received validation for record");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(record);
        if (constraintViolations.size() > 0) {
            String constraintViolationsMessage = "";
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                constraintViolationsMessage = constraintViolationsMessage + constraintViolation.getMessage() + "。";
            }
            UUID exuuid = UUID.randomUUID();
            String trace = "exception UUID=" + exuuid + " body is invalid ";
            String message = constraintViolationsMessage;
            String code = ERROR_TYPE + "CORE01POS";
            // logger.error(trace, httpRequest.getContextPath());
            throw new CustomBadRequestException(null, message, trace, code, exuuid);
        }
    }

    public static <T> void BeanValidation(T[] records, String ERROR_TYPE) {
        logger.info("received validation for records");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        for (T record : records) {
            Set<ConstraintViolation<T>> constraintViolations = validator.validate(record);
            if (constraintViolations.size() > 0) {
                String constraintViolationsMessage = "";
                for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                    constraintViolationsMessage = constraintViolationsMessage + constraintViolation.getMessage() + "。";
                }
                UUID exuuid = UUID.randomUUID();
                String trace = "exception UUID=" + exuuid + " body is invalid ";
                String message = constraintViolationsMessage;
                String code = ERROR_TYPE + "CORE01POS";
                // logger.error(trace, httpRequest.getContextPath());
                throw new CustomBadRequestException(null, message, trace, code, exuuid);
            }
        }
    }
}
