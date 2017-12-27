package com.cts.pmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectManagementApplication {

    private static final Logger logger = LoggerFactory.getLogger(ProjectManagementApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementApplication.class, args);
    }


}
