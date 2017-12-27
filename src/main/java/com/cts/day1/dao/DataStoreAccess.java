package com.cts.day1.dao;

import com.google.cloud.datastore.Entity;

import java.util.Iterator;

public interface DataStoreAccess {
    void retrieveAllService();

    Iterator<Entity> listTasks();
}
