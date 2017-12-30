package com.cts.pmt.parenttask.model;

import com.cts.pmt.task.model.Task;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "parent_task")
public class ParentTask {

    private long id;
    private String name;
    private Set<Task> tasks;

    public ParentTask() {
    }

    public ParentTask(String name) {
        this.name = name;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL)
    public Set<Task> getTasks() {
        return tasks;
    }


    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "ParentTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}

