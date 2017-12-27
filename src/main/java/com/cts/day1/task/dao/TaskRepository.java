package com.cts.day1.task.dao;


import com.cts.day1.task.model.Task;
import com.cts.day1.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{


}
