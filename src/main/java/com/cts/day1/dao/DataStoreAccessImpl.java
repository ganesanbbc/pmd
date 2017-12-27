package com.cts.day1.dao;

import com.google.cloud.datastore.*;

import java.util.Iterator;

public class DataStoreAccessImpl implements DataStoreAccess {


    public static final String KIND = "Task";

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    @Override
    public void retrieveAllService() {

        // The name/ID for the new entity
        String name = "sampletask1";

        // The Cloud Datastore key for the new entity
        Key taskKey = datastore.newKeyFactory().setKind(KIND).newKey(name);

        // Prepares the new entity
        Entity task = Entity.newBuilder(taskKey)
                .set("description", "Buy milk")
                .build();

        // Saves the entity
        datastore.put(task);

        System.out.printf("Saved %s: %s%n", task.getKey().getName(), task.getString("description"));

        //Retrieve entity
        Entity retrieved = datastore.get(taskKey);

        System.out.printf("Retrieved %s: %s%n", taskKey.getName(), retrieved.getString("description"));
    }

    @Override
    public Iterator<Entity> listTasks() {
        Query<Entity> query =
                Query.newEntityQueryBuilder().setKind(KIND).build();
        return datastore.run(query);
    }

}
