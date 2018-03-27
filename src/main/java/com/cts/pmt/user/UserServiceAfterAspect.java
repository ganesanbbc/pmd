package com.cts.pmt.user;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserServiceAfterAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //What kind of method calls I would intercept
    //execution(* PACKAGE.*.*(..))
    //Weaving & Weaver
    @AfterReturning(value = "execution(* com.cts.pmt.user.**.*.*(..))",returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("--AFTER-----{} returned with value {}", joinPoint, result);
    }
}