package com.cts.pmt;


import com.cts.pmt.parenttask.model.ParentTask;
import com.cts.pmt.task.model.Task;
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
public class TaskModuleIntegrationTest {

    public static final String GET_TASK = "/tasks";
    public static final String GET_TASK_BY_ID = "/tasks/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    JdbcTemplate template;

    @Test
    public void thatGetAllTaskWhenRequestingAllTask() throws Exception {

        byte[] encoded = Files.readAllBytes(Paths.get(new ClassPathResource("task_statements.sql")
                .getFile().getAbsolutePath()));
        String sql = new String(encoded);
        template.execute(sql);

        ResponseEntity<Task[]> responseEntity =
                restTemplate.getForEntity(GET_TASK, Task[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Task[] body = responseEntity.getBody();
        ArrayList<Task> list = new ArrayList(Arrays.asList(body));

        assertTrue(list.size() > 0);
    }


    @Test
    public void thatGetTaskWhenRequestingTaskById() throws Exception {

        byte[] encoded = Files.readAllBytes(Paths.get(
                new ClassPathResource("task_statements.sql").getFile().getAbsolutePath()));
        String sql = new String(encoded);
        template.execute(sql);


        ResponseEntity<Task[]> responseEntity =
                restTemplate.getForEntity(GET_TASK, Task[].class);
        Task[] body = responseEntity.getBody();
        ArrayList<Task> list = new ArrayList(Arrays.asList(body));
        long id = list.get(0).getId();

        System.out.println(list);

        ResponseEntity<Task> responseEntity1 =
                restTemplate.getForEntity(GET_TASK_BY_ID + id, Task.class);
        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());

        System.out.println(responseEntity1.getBody());
    }


    @Test
    public void thatGetNotFoundExceptiontWhenRequestingInvalidTaskId() throws Exception {


        ResponseEntity<Task> responseEntity1 =
                restTemplate.getForEntity(GET_TASK_BY_ID + 100, Task.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity1.getStatusCode());
    }


    @Test
    public void thatAddTaskWhenRequestingAddTask() throws Exception {

        Task user = new Task("UserName");
        ResponseEntity<Task[]> responseEntity =
                restTemplate.postForEntity(GET_TASK, user, Task[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void thatUpdateTaskWhenRequestingUpdateTask() throws Exception {


        ResponseEntity<Task[]> responseEntity =
                restTemplate.getForEntity(GET_TASK, Task[].class);
        Task[] body = responseEntity.getBody();
        ArrayList<Task> list = new ArrayList(Arrays.asList(body));

        Task user = list.get(0);
        user.setName("UpdatedName");

        ResponseEntity<Task> responseEntity1 =
                restTemplate.postForEntity(GET_TASK_BY_ID + user.getId(), user, Task.class);
        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());

        System.out.println(responseEntity1.getBody());
    }


}