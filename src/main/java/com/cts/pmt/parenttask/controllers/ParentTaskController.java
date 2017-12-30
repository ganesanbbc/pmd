package com.cts.pmt.parenttask.controllers;


import com.cts.pmt.parenttask.exception.ParentTaskException;
import com.cts.pmt.parenttask.model.ParentTask;
import com.cts.pmt.parenttask.service.ParentTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/parenttasks")
public class ParentTaskController {


    @Autowired
    private ParentTaskService service;

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<ParentTask>> getAllUsers() {
        return new ResponseEntity<List<ParentTask>>(service.getAll(), OK);
    }


    @RequestMapping(path = "/{id}", method = GET)
    public @ResponseBody
    ResponseEntity<ParentTask> getParentTask(@PathVariable("id") long id) throws ParentTaskException {
        ParentTask response = service.getById(id);
        if (response == null) {
            throw new ParentTaskException("ParentTask is not found");
        }
        return new ResponseEntity<ParentTask>(response, OK);
    }


    @PostMapping
    public List<ParentTask> updateItemById(@RequestBody ParentTask body) throws ParentTaskException {
        service.save(body);
        return service.getAll();
    }


}