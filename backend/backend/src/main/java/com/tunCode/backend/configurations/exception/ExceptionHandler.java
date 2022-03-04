package com.tunCode.backend.configurations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {EmployeeAlreadyExistsException.class})
    public ResponseEntity<Object> handleAlreadyExistsException(EmployeeAlreadyExistsException exc){
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        ErrorResponse errorResponse = new ErrorResponse(
                exc.getMessage(),
                HttpStatus.CONFLICT.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<Object>(errorResponse,httpStatus);
    }
}
