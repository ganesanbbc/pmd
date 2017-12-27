package com.cts.day1.project.service;

import com.cts.day1.project.controllers.ProjectController;
import com.cts.day1.project.dao.ProjectRepository;
import com.cts.day1.project.model.Project;
import com.cts.day1.task.dao.TaskRepository;
import com.cts.day1.task.model.Task;
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
        List<Project> list = repository.findAll();
        for (Project project : list) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }

    public List<Project> getAll() {
        return repository.findAll();
    }
}
