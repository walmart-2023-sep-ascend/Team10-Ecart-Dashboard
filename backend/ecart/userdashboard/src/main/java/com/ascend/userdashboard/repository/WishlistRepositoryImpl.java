package com.ascend.userdashboard.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

import com.ascend.userdashboard.model.Wishlist;
import com.ascend.userdashboard.model.WishlistProducts;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/*
 * Requires the MongoDB Java Driver.
 * https://mongodb.github.io/mongo-java-driver
 */


@Repository
public class WishlistRepositoryImpl implements WishlistRepository {
	@Autowired
	MongoClient mongoClient ;
	
	@Autowired
	MongoConverter converter;
	@Override
	public List<Wishlist> getUserWishlist(String userEmail) {
		MongoDatabase database = mongoClient.getDatabase("ecart");
		MongoCollection<Document> collection = database.getCollection("wishlist");

		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
		    new Document("text", 
		    new Document("query", userEmail)
		                .append("path", "userId")))));
		List<Wishlist> productIdList = new ArrayList<Wishlist> ();
		result.forEach(doc -> productIdList.add(converter.read(Wishlist.class, doc)));
		
		return productIdList;
	}

}
