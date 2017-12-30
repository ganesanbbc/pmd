package com.cts.pmt.task.model;

import com.cts.pmt.parenttask.model.ParentTask;
import com.cts.pmt.project.model.Project;
import com.cts.pmt.user.model.User;

import javax.persistence.*;

@Entity
public class Task {

    private long id;
    private String name;
    private ParentTask parentTask;
    private Project project;
    private User user;

    public Task() {
    }

    public Task(String name, ParentTask parentTask) {
        this.name = name;
        this.parentTask = parentTask;
    }

    public Task(String name) {

        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    public ParentTask getParentTask() {
        return parentTask;
    }

    public void setParentTask(ParentTask parentTask) {
        this.parentTask = parentTask;
    }

    @ManyToOne
    @JoinColumn(name = "project_id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentTask=" + parentTask +
                ", project=" + project +
                ", user=" + user +
                '}';
    }
}

