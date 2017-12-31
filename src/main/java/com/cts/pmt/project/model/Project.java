package com.cts.pmt.project.model;

import com.cts.pmt.task.model.Task;
import com.cts.pmt.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project implements Serializable {

    private long id;
    private String name;
    private String startDate;
    private String endDate;
    private String status;
    private String priority;


    private Set<Task> tasks;
    private Set<User> users;

    public Project() {
    }

    public Project(String name) {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @OneToMany(mappedBy = "project", cascade = {CascadeType.MERGE, CascadeType.ALL}, fetch = FetchType.EAGER)
    @JsonBackReference
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }


    @OneToMany(mappedBy = "project", cascade = {CascadeType.MERGE, CascadeType.ALL}, fetch = FetchType.EAGER)
    @JsonManagedReference
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


}
