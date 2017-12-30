package com.cts.pmt.user.model;

import com.cts.pmt.project.model.Project;
import com.cts.pmt.task.model.Task;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    private long id;
    private String name;
    private Set<Task> tasks;
    private Project project;


    public User() {
    }

    public User(String name) {
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

    @ManyToOne
    @JoinColumn(name = "project_id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                ", project=" + project +
                '}';
    }
}

