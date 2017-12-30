package com.cts.pmt.task.controllers;


import com.cts.pmt.task.model.Task;
import com.cts.pmt.task.service.TaskService;
import com.cts.pmt.user.exception.UserException;
import com.cts.pmt.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    @Autowired
    private TaskService service;

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<Task>> getAllUsers() {
        return new ResponseEntity<List<Task>>(service.getAll(), OK);
    }

    @RequestMapping(path = "/{id}", method = GET)
    public @ResponseBody
    ResponseEntity<Task> getProduct(@PathVariable("id") long id) throws UserException {
        Task response = service.getById(id);
        if (response == null) {
            throw new UserException("ParentTask is not found");
        }
        return new ResponseEntity<Task>(response, OK);
    }


    @PostMapping
    public List<Task> updateItemById(@RequestBody Task body) throws UserException {
        service.save(body);
        return service.getAll();
    }

    @PostMapping(path = "/{id}")
    public Task updateInfo(@PathVariable("id") long id, @RequestBody Task body) throws UserException {
        Task response = service.getById(id);
        response.setName(body.getName());
        service.save(response);
        return response;
    }


}