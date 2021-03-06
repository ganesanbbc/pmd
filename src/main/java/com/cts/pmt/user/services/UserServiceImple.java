package com.cts.pmt.user.services;

import com.cts.pmt.user.TrackTime;
import com.cts.pmt.user.dao.UserRepository;
import com.cts.pmt.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserRepository repository;

    public void save(User item) {
        repository.save(item);
    }

    @Override
    public User getById(long id) {
        List<User> list = (List<User>) repository.findAll();
        for (User user : list) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @TrackTime
    @Override
    public String doSomething() {

        System.out.println("Doing some business logic.......................");
        System.out.println("Doing some business logic.......................");
        System.out.println("Doing some business logic.......................");
        System.out.println("Doing some business logic.......................");
        System.out.println("Doing some business logic.......................");
        System.out.println("Doing some business logic.......................");
        return "AOP";
    }

    public List<User> getAll() {
        return (List<User>) repository.findAll();
    }
}
