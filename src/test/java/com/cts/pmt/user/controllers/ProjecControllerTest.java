package com.cts.pmt.user.controllers;


import com.cts.pmt.ProjectManagementApplication;
import com.cts.pmt.project.model.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ProjectManagementApplication.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class ProjecControllerTest {

    public static final String GET_PRODUCTS = "/projects";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    JdbcTemplate template;

    @Test
    public void twoLocations() throws Exception {

        byte[] encoded = Files.readAllBytes(Paths.get(new ClassPathResource("project_statements.sql").getFile().getAbsolutePath()));
        String sql = new String(encoded);
        template.execute(sql);

        ResponseEntity<Project[]> responseEntity =
                restTemplate.getForEntity(GET_PRODUCTS, Project[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


        Project[] body = responseEntity.getBody();
        ArrayList<Object> list = new ArrayList(Arrays.asList(body));

        System.out.println(body.length);
        System.out.println(list);
    }


}