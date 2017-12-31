package com.cts.pmt;


import com.cts.pmt.ProjectManagementApplication;
import com.cts.pmt.project.model.Project;
import com.cts.pmt.user.model.User;
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
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ProjectManagementApplication.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class ProjectModuleIntegrationTest {

    public static final String GET_PRODUCTS = "/projects";
    public static final String GET_PRODUCT_BY_ID = "/projects/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    JdbcTemplate template;

    @Test
    public void thatGetProjectListWhenRequestingProjects() throws Exception {

        byte[] encoded = Files.readAllBytes(Paths.get(new ClassPathResource("project_statements.sql").getFile().getAbsolutePath()));
        String sql = new String(encoded);
        template.execute(sql);

        ResponseEntity<Project[]> responseEntity =
                restTemplate.getForEntity(GET_PRODUCTS, Project[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Project[] body = responseEntity.getBody();
        ArrayList<Project> list = new ArrayList(Arrays.asList(body));

        assertTrue(list.size() > 0);
    }


    @Test
    public void thatGetProjectWhenRequestingProjectById() throws Exception {

        byte[] encoded = Files.readAllBytes(Paths.get(
                new ClassPathResource("project_statements.sql").getFile().getAbsolutePath()));
        String sql = new String(encoded);
        template.execute(sql);


        ResponseEntity<Project[]> responseEntity =
                restTemplate.getForEntity(GET_PRODUCTS, Project[].class);
        Project[] body = responseEntity.getBody();

        ArrayList<Project> list = new ArrayList(Arrays.asList(body));

        long id = list.get(0).getId();
        ResponseEntity<Project> responseEntity1 =
                restTemplate.getForEntity(GET_PRODUCT_BY_ID + id, Project.class);
        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());

        System.out.println(responseEntity1.getBody());
    }


    @Test
    public void thatGetNotFoundExceptiontWhenRequestingInvalidProjectId() throws Exception {
        ResponseEntity<Project> responseEntity1 =
                restTemplate.getForEntity(GET_PRODUCT_BY_ID + 100, Project.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity1.getStatusCode());
    }


    @Test
    public void thatAddProjectWhenRequestingAddProject() throws Exception {

        Project project = new Project("TestProject");
        project.setName("SomeProjectName");
        project.setStartDate("12-12-2017");
        project.setEndDate("22-12-2017");
        project.setPriority("Small");
        project.setStatus("Open");

        ResponseEntity<Project[]> responseEntity =
                restTemplate.postForEntity(GET_PRODUCTS, project, Project[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void thatAddProjectWhenRequestingAddProjectContainsNewUser() throws Exception {

        Project project = new Project("TestProject");
        Set<User> users = new HashSet<>();
        users.add(new User("name"));
        project.setUsers(users);
        ResponseEntity<Project[]> responseEntity =
                restTemplate.postForEntity(GET_PRODUCTS, project, Project[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void thatAddProjectWhenRequestingAddProjectContainsExistingUser() throws Exception {

        byte[] encoded = Files.readAllBytes(Paths.get(
                new ClassPathResource("user_statements.sql").getFile().getAbsolutePath()));
        String sql = new String(encoded);
        template.execute(sql);




        Project project = new Project("TestProject");
        Set<User> users = new HashSet<>();
        users.add(new User("some name"));
        project.setUsers(users);
        ResponseEntity<Project[]> responseEntity =
                restTemplate.postForEntity(GET_PRODUCTS, project, Project[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }



    @Test
    public void thatUpdateProjectWhenRequestingUpdateProject() throws Exception {


        ResponseEntity<Project[]> responseEntity =
                restTemplate.getForEntity(GET_PRODUCTS, Project[].class);
        Project[] body = responseEntity.getBody();
        ArrayList<Project> list = new ArrayList(Arrays.asList(body));

        Project project = list.get(0);
        project.setName("UpdatedName");

        ResponseEntity<Project> responseEntity1 =
                restTemplate.postForEntity(GET_PRODUCT_BY_ID + project.getId(), project, Project.class);
        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());

        System.out.println(responseEntity1.getBody());
    }


}