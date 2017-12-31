package com.cts.pmt.project.service;

import com.cts.pmt.project.dao.ProjectRepository;
import com.cts.pmt.project.model.Project;
import com.cts.pmt.user.dao.UserRepository;
import com.cts.pmt.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository repository;

    @Autowired
    UserRepository userRepository;

    public void save(Project item) {
        if (item.getUsers() != null) {
            Set<User> users = new HashSet();
            for (User user : item.getUsers()) {
                List<User> byName = userRepository.findByName(user.getName());
                if (byName != null && byName.size() > 0) {
                    User existingUser = (User) byName.get(0);
                    if (existingUser != null) {
                        users.add(existingUser);
                    }
                } else {
                    users.add(user);
                }
            }
            item.setUsers(users);
        }
        repository.save(item);
    }

    public Project getById(long id) {
        List<Project> list = (List<Project>) repository.findAll();
        for (Project project : list) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }

    public List<Project> getAll() {
        List<Project> projectList = (List<Project>) repository.findAll();

        for (Project project : projectList) {
            if (project.getUsers() != null) {
                for (User user : project.getUsers()) {
                    user.setProject(null);
                }
            }

        }

        return projectList;
    }
}
