package com.example.Connection;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    public static MongoDatabase connectToDatabase() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        return mongoClient.getDatabase("BancoTest");
    }
}
