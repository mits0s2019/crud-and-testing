package com.example.userservice.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({HibernateException.class, PersistenceException.class})
    public final ResponseEntity<ExceptionResponse> handlHibernateEx(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity handleUserNotFoundEx(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Wrong ID",
                request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        StringBuilder details = new StringBuilder();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.append(error.getDefaultMessage());
            details.append(".");
        }
        ExceptionResponse error = new ExceptionResponse(new Date(),"Validation Failed", details.toString());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
