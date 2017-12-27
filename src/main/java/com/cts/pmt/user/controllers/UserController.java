package com.cts.pmt.user.controllers;


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
    ResponseEntity<User> getProduct(@PathVariable("id") long id) throws UserException {
        User response = service.getById(id);
        if (response == null) {
            throw new UserException("Product is not found");
        }
        return new ResponseEntity<User>(response, OK);
    }


    @PostMapping
    public List<User> updateItemById(@RequestBody User product) throws UserException {
        service.save(product);
        return service.getAll();
    }


}