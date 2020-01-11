package com.example.userservice.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class UserControllerAOP {

    private  Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.example.userservice.controller.UserController.*(..))")
    public void userControllerMethods(){};

    @Before("userControllerMethods()")
    public void userContollerAdvice(JoinPoint jp){
        MethodSignature methodSignature =(MethodSignature) jp.getSignature();
        logger.info("====>>>> "+methodSignature.getMethod().getName());
    }
}
