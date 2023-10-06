package com.wm.ECartPGPTeamTen.dao;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.wm.ECartPGPTeamTen.model.PromotionsModel;

@Repository
public class PromotionsDao {

	private static final Logger logger = LoggerFactory.getLogger(PromotionsDao.class);

	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<PromotionsModel> getActivePromotinos(LocalDate date) throws Exception {
		logger.debug("Get product based on user recent searches.....!");

		try {
			if (date != null ) {
				String a = date.toString();
				
				String q = "{ $and: [ {startDate: { $gt: ISODate('"+a+"T00:00:00.000Z') }}, {status :{$eq : \"active\"}} ] }";
				BasicQuery query = new BasicQuery(q);
				return mongoTemplate.find(query, PromotionsModel.class);
			}

		} catch (Exception e) {
			logger.error("Error occured " , e);
			throw new Exception("Error occured");
		}
		return null;
	}

	
}
