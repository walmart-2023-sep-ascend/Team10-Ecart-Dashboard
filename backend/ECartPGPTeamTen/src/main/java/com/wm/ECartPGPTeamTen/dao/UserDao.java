package com.wm.ECartPGPTeamTen.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.wm.ECartPGPTeamTen.model.UserModel;

/**
 * r0m09yu
 */

@Repository
public class UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public List<UserModel> findById(Integer userId) {
		logger.debug("Get Details by Id");
		BasicQuery query = new BasicQuery("{ id : { $eq : " + userId + " }}");
		return mongoTemplate.find(query, UserModel.class);
	}

	public List<UserModel> findUserSearchesById(Integer userId) {
		logger.debug("Get User searches..!");

		BasicQuery query = new BasicQuery("{ id : { $eq : " + userId + " }}");
		query.fields().include("recentSearches", "id");
		return mongoTemplate.find(query, UserModel.class);
	}

	public List<UserModel> findUserfavbrandsById(Integer userId) {
		logger.debug("Get User searches..!");

		BasicQuery query = new BasicQuery("{ id : { $eq : " + userId + " }}");
		query.fields().include("favoriteCategories", "id");
		return mongoTemplate.find(query, UserModel.class);
	}
}
