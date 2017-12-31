package com.cts.pmt.user.controllers;


import com.cts.pmt.project.model.Project;
import com.cts.pmt.user.exception.UserException;
import com.cts.pmt.user.model.User;
import com.cts.pmt.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {


    @Autowired
    private UserService service;

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(service.getAll(), OK);
    }


    @RequestMapping(path = "/{id}", method = GET)
    public @ResponseBody
    ResponseEntity<User> getUser(@PathVariable("id") long id) throws UserException {
        User response = service.getById(id);
        if (response == null) {
            throw new UserException("User is not found");
        }
        return new ResponseEntity<User>(response, OK);
    }


    @PostMapping
    public List<User> updateUser(@RequestBody User product) throws UserException {
        service.save(product);
        return service.getAll();
    }

    @PostMapping(path = "/{id}")
    public User updateUserInfo(@PathVariable("id") long id, @RequestBody User body) throws UserException {
        User response = service.getById(id);
        response.setName(body.getName());
        service.save(response);
        return response;
    }

}