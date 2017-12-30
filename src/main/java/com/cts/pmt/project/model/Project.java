package com.cts.pmt.project.model;

import com.cts.pmt.task.model.Task;
import com.cts.pmt.user.model.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {

    private long id;
    private String name;
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

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                ", users=" + users +
                '}';
    }
}
