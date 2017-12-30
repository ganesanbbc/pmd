package com.cts.pmt.task.service;

import com.cts.pmt.task.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    public void save(Task item) {
//        repository.save(item);
    }

    public Task getById(long id) {
//        List<Task> list = repository.findAll();
//        for (Task user : list) {
//            if (user.getId() == id) {
//                return user;
//            }
//        }
        return null;
    }

    public List<Task> getAll() {
        return null;
    }
}
