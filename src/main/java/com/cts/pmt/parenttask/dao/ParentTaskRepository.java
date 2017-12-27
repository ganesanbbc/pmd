package com.cts.pmt.parenttask.dao;


import com.cts.pmt.parenttask.model.ParentTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTask, Long>{


}
