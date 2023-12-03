package com.ascend.userdashboard.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.ascend.userdashboard.model.User;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class SearchRepositoryImpl implements SearchRepository{
	@Autowired
	MongoClient mongoClient;

	@Autowired
	MongoConverter converter;

	@Override
	public List<User> getUserSearches(int userId){
		 List<User> users = new ArrayList();
		MongoDatabase database = mongoClient.getDatabase("ecart");
		MongoCollection<Document> collection = database.getCollection("user");

		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
			    new Document("equals", 
			    new Document("path", "id")
			                .append("value", userId)))));
		result.forEach(doc -> users.add(converter.read(User.class,doc)));
		System.out.println(users);		
		return users;
	}
	}

