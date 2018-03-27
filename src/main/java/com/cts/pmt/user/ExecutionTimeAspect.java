package com.cts.pmt.user;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ExecutionTimeAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Around("@annotation(com.cts.pmt.user.TrackTime)")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        joinPoint.proceed();

        logger.info("Time taken::" + (System.currentTimeMillis() - startTime));

    }


}
