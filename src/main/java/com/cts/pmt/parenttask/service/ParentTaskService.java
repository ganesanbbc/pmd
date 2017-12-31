package com.cts.pmt.parenttask.service;

import com.cts.pmt.parenttask.dao.ParentTaskRepository;
import com.cts.pmt.parenttask.model.ParentTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentTaskService {

    @Autowired
    private ParentTaskRepository repository;

    public void save(ParentTask item) {
        repository.save(item);
    }

    public ParentTask getById(long id) {
        List<ParentTask> list = (List<ParentTask>) repository.findAll();
        for (ParentTask user : list) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public List<ParentTask> getAll() {
        return (List<ParentTask>) repository.findAll();
    }
}
