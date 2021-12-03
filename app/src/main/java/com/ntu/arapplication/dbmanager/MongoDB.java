package com.ntu.arapplication.dbmanager;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoDB {
    private final String URI = "mongodb+srv://taras:varlist@cluster0.vqbi5.mongodb.net/arsdk";

    MongoDB() {
        try (MongoClient mongoClient = (MongoClient) MongoClients.create(URI)) {
            MongoIterable<String> strings = mongoClient.listDatabaseNames();
            MongoCursor<String> cursor = strings.cursor();
            while (cursor.hasNext()){
                System.out.println(cursor.next());
            }
        }
    }
}
