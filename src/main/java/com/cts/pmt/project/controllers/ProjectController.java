package com.cts.pmt.project.controllers;


import com.cts.pmt.project.exception.ProjectException;
import com.cts.pmt.project.model.Project;
import com.cts.pmt.project.service.ProjectService;
import com.cts.pmt.user.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/projects")
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
    ResponseEntity<Project> getProduct(@PathVariable("id") long id) throws ProjectException {
        Project response = service.getById(id);
        if (response == null) {
            throw new ProjectException("Project is not found");
        }
        return new ResponseEntity<Project>(response, OK);
    }


    @PostMapping
    public List<Project> addProject(@RequestBody Project body) throws ProjectException {
        service.save(body);
        return service.getAll();
    }


    @PostMapping(path = "/{id}")
    public Project updateProject(@PathVariable("id") long id, @RequestBody Project body) throws ProjectException {
        Project response = service.getById(id);
        response.setName(body.getName());
        service.save(response);
        return response;
    }


}