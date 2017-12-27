package com.cts.day1.project.controllers;


import com.cts.day1.ProjectManagementApplication;
import com.cts.day1.project.model.Project;
import com.cts.day1.project.service.ProjectService;
import com.cts.day1.task.model.Task;
import com.cts.day1.task.service.TaskService;
import com.cts.day1.user.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService service;

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<Project>> getAllProject() {
        return new ResponseEntity<List<Project>>(service.getAll(), OK);
    }


    @RequestMapping(path = "/{id}", method = GET)
    public @ResponseBody
    ResponseEntity<Project> getProduct(@PathVariable("id") long id) throws UserException {
        Project response = service.getById(id);
        if (response == null) {
            throw new UserException("Task is not found");
        }
        return new ResponseEntity<Project>(response, OK);
    }


    @PostMapping
    public List<Project> updateItemById(@RequestBody Project body) throws UserException {
        service.save(body);
        return service.getAll();
    }


}