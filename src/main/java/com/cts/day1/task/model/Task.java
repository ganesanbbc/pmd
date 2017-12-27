package com.cts.day1.task.model;

import javax.persistence.Entity;

@Entity
public class Task {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
