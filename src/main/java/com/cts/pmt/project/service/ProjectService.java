package com.cts.pmt.project.service;

import com.cts.pmt.project.dao.ProjectRepository;
import com.cts.pmt.project.model.Project;
import com.cts.pmt.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository repository;

    public void save(Project item) {
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
