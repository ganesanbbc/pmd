package com.cts.pmt.user.services;

import com.cts.pmt.user.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void save(User product);

    User getById(long expectedProductID);

    String doSomething();
}
