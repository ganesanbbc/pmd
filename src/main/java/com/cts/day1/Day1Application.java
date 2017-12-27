package com.cts.day1;

import com.cts.day1.dao.ProductJPARepository;
import com.cts.day1.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Day1Application {

    private static final Logger logger = LoggerFactory.getLogger(Day1Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Day1Application.class, args);
    }


}
