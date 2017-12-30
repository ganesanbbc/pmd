package com.cts.pmt;

import com.cts.pmt.parenttask.dao.ParentTaskRepository;
import com.cts.pmt.parenttask.model.ParentTask;
import com.cts.pmt.project.dao.ProjectRepository;
import com.cts.pmt.project.model.Project;
import com.cts.pmt.task.dao.TaskRepository;
import com.cts.pmt.task.model.Task;
import com.cts.pmt.user.dao.UserRepository;
import com.cts.pmt.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ProjectManagementApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ProjectManagementApplication.class);

    @Autowired
    private ParentTaskRepository parentTaskRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {

        saveAndGet();

//
//        User dbUser = userRepository.findOne(9l);
//        Set<User> user = new HashSet<>();
//        user.add(dbUser);
//
//        Project entity1 = new Project("My project");
//        entity1.setUsers(user);
//        projectRepository.save(entity1);
//
//        for (Project parentTask : projectRepository.findAll()) {
//            System.out.println(parentTask);
//        }



    }

    private void saveAndGet() {
        ParentTask item = new ParentTask("ParentTask1");
        parentTaskRepository.save(item);

        for (ParentTask parentTask : parentTaskRepository.findAll()) {
            System.out.println(parentTask);
        }

//        Task entity = new Task("My Task");
//        taskRepository.save(entity);
//
//        for (Task task : taskRepository.findAll()) {
//            System.out.println(task);
//        }
//
//        Project entity1 = new Project("My project");
//        projectRepository.save(entity1);
//
//        for (Project project : projectRepository.findAll()) {
//            System.out.println(project);
//        }
//
//        User entity2 = new User("USerName");
//        userRepository.save(entity2);
//
//        for (User user : userRepository.findAll()) {
//            System.out.println(user);
//        }
    }
}
