package com.example.userservice.exception;

import java.util.Date;
import javax.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler({HibernateException.class, PersistenceException.class})
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
