package com.ascend.userdashboard.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

import com.ascend.userdashboard.model.Products;
import com.ascend.userdashboard.model.User;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/*
 * Requires the MongoDB Java Driver.
 * https://mongodb.github.io/mongo-java-driver
 */


@Repository
public class ProductRepositoryImpl implements ProductRepository {
	@Autowired
	MongoClient mongoClient ;
	
	@Autowired
	MongoConverter converter;
	@Override
	public List<Products> getProducts(String query, String path) {
		MongoDatabase database = mongoClient.getDatabase("ecart");
		MongoCollection<Document> collection = database.getCollection("products");

		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
		    new Document("text", 
		    new Document("query", query)
		                .append("path", path)))));
		List<Products> productsList = new ArrayList<Products> ();
		result.forEach(doc -> productsList.add(converter.read(Products.class, doc)));
		
		return productsList;
	}

	
	@Override
	public List<Products> getProductsById( int value) {
		MongoDatabase database = mongoClient.getDatabase("ecart");
		MongoCollection<Document> collection = database.getCollection("products");

		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
		    new Document("equals", 
		    new Document("path", "id")
		                .append("value",value)))));
		List<Products> productsList = new ArrayList<Products> ();
		result.forEach(doc -> productsList.add(converter.read(Products.class, doc)));
		
		return productsList;
	}
}
