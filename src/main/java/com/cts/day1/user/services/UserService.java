package com.cts.day1.user.services;

import com.cts.day1.user.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void save(User product);

    User getById(long expectedProductID);
}
