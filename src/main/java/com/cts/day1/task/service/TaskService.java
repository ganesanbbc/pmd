package com.cts.day1.task.service;

import com.cts.day1.task.dao.TaskRepository;
import com.cts.day1.task.model.Task;
import com.cts.day1.user.dao.UserRepository;
import com.cts.day1.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public void save(Task item) {
        repository.save(item);
    }

    public Task getById(long id) {
        List<Task> list = repository.findAll();
        for (Task user : list) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }
}
