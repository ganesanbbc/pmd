package com.cts.pmt;


import com.cts.pmt.parenttask.model.ParentTask;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ProjectManagementApplication.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class ParentTaskModuleIntegrationTest {

    public static final String GET_PARENT_TASK = "/parenttasks";
    public static final String GET_PARENT_TASK_BY_ID = "/parenttasks/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    JdbcTemplate template;

    @Test
    public void thatGetAllParentTaskWhenRequestingAllParentTask() throws Exception {

        byte[] encoded = Files.readAllBytes(Paths.get(new ClassPathResource("parent_task_statements.sql").getFile().getAbsolutePath()));
        String sql = new String(encoded);
        template.execute(sql);

        ResponseEntity<ParentTask[]> responseEntity =
                restTemplate.getForEntity(GET_PARENT_TASK, ParentTask[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ParentTask[] body = responseEntity.getBody();
        ArrayList<ParentTask> list = new ArrayList(Arrays.asList(body));

        assertTrue(list.size() > 0);
    }


    @Test
    public void thatGetUserWhenRequestingUserById() throws Exception {

        byte[] encoded = Files.readAllBytes(Paths.get(
                new ClassPathResource("parent_task_statements.sql").getFile().getAbsolutePath()));
        String sql = new String(encoded);
        template.execute(sql);


        ResponseEntity<ParentTask[]> responseEntity =
                restTemplate.getForEntity(GET_PARENT_TASK, ParentTask[].class);
        ParentTask[] body = responseEntity.getBody();
        ArrayList<ParentTask> list = new ArrayList(Arrays.asList(body));
        long id = list.get(0).getId();

        System.out.println(list);

        ResponseEntity<ParentTask> responseEntity1 =
                restTemplate.getForEntity(GET_PARENT_TASK_BY_ID + id, ParentTask.class);
        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());

        System.out.println(responseEntity1.getBody());
    }


    @Test
    public void thatGetNotFoundExceptiontWhenRequestingInvalidParentTaskId() throws Exception {


        ResponseEntity<ParentTask> responseEntity1 =
                restTemplate.getForEntity(GET_PARENT_TASK_BY_ID + 100, ParentTask.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity1.getStatusCode());
    }


    @Test
    public void thatAddParentTaskWhenRequestingAddParentTask() throws Exception {

        ParentTask user = new ParentTask("UserName");
        ResponseEntity<ParentTask[]> responseEntity =
                restTemplate.postForEntity(GET_PARENT_TASK, user, ParentTask[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void thatUpdateParentTaskWhenRequestingUpdateParentTask() throws Exception {


        ResponseEntity<ParentTask[]> responseEntity =
                restTemplate.getForEntity(GET_PARENT_TASK, ParentTask[].class);
        ParentTask[] body = responseEntity.getBody();
        ArrayList<ParentTask> list = new ArrayList(Arrays.asList(body));

        ParentTask user = list.get(0);
        user.setName("UpdatedName");

        ResponseEntity<ParentTask> responseEntity1 =
                restTemplate.postForEntity(GET_PARENT_TASK_BY_ID + user.getId(), user, ParentTask.class);
        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());

        System.out.println(responseEntity1.getBody());
    }


}