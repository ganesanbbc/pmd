package com.cts.pmt.project.service;

import com.cts.pmt.project.dao.ProjectRepository;
import com.cts.pmt.project.model.Project;
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
