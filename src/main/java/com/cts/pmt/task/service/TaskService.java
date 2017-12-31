package com.cts.pmt.task.service;

import com.cts.pmt.task.dao.TaskRepository;
import com.cts.pmt.task.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    @Autowired
    TaskRepository repository;


    public void save(Task item) {
        repository.save(item);
    }

    public Task getById(long id) {
        List<Task> list = (List<Task>) repository.findAll();
        for (Task task : list) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public List<Task> getAll() {
        return (List<Task>) repository.findAll();
    }
}
