package com.cts.day1.user.services;

import com.cts.day1.user.dao.UserRepository;
import com.cts.day1.user.model.User;
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
        List<User> list = repository.findAll();
        for (User user : list) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAll() {
        return repository.findAll();
    }
}
