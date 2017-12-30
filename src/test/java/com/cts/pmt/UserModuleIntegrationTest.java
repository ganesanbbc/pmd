package com.cts.pmt;


import com.cts.pmt.user.model.User;
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
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ProjectManagementApplication.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class UserModuleIntegrationTest {

    public static final String GET_USERS = "/users";
    public static final String GET_USER_BY_ID = "/users/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    JdbcTemplate template;

    @Test
    public void thatGetAllUsersWhenRequestingAllUsers() throws Exception {

        byte[] encoded = Files.readAllBytes(Paths.get(new ClassPathResource("user_statements.sql").getFile().getAbsolutePath()));
        String sql = new String(encoded);
        template.execute(sql);

        ResponseEntity<User[]> responseEntity =
                restTemplate.getForEntity(GET_USERS, User[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        User[] body = responseEntity.getBody();
        ArrayList<User> list = new ArrayList(Arrays.asList(body));

        assertTrue(list.size() > 0);
    }


    @Test
    public void thatGetUserWhenRequestingUserById() throws Exception {

        byte[] encoded = Files.readAllBytes(Paths.get(
                new ClassPathResource("user_statements.sql").getFile().getAbsolutePath()));
        String sql = new String(encoded);
        template.execute(sql);


        ResponseEntity<User[]> responseEntity =
                restTemplate.getForEntity(GET_USERS, User[].class);
        User[] body = responseEntity.getBody();
        ArrayList<User> list = new ArrayList(Arrays.asList(body));
        long id = list.get(0).getId();

        System.out.println(list);

        ResponseEntity<User> responseEntity1 =
                restTemplate.getForEntity(GET_USER_BY_ID + id, User.class);
        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());

        System.out.println(responseEntity1.getBody());
    }


    @Test
    public void thatGetNotFoundExceptiontWhenRequestingInvalidProjectId() throws Exception {


        ResponseEntity<User> responseEntity1 =
                restTemplate.getForEntity(GET_USER_BY_ID + 100, User.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity1.getStatusCode());
    }


    @Test
    public void thatAddUserWhenRequestingAddUser() throws Exception {

        User user = new User("UserName");
        ResponseEntity<User[]> responseEntity =
                restTemplate.postForEntity(GET_USERS, user, User[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void thatUpdateUserWhenRequestingUpdateUser() throws Exception {


        ResponseEntity<User[]> responseEntity =
                restTemplate.getForEntity(GET_USERS, User[].class);
        User[] body = responseEntity.getBody();
        ArrayList<User> list = new ArrayList(Arrays.asList(body));

        User user = list.get(0);
        user.setName("UpdatedName");

        ResponseEntity<User> responseEntity1 =
                restTemplate.postForEntity(GET_USER_BY_ID + user.getId(), user, User.class);
        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());

        System.out.println(responseEntity1.getBody());
    }


}