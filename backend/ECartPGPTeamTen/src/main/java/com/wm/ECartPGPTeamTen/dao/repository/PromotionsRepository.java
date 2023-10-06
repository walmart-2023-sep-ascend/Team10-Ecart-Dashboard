package com.wm.ECartPGPTeamTen.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wm.ECartPGPTeamTen.model.PromotionsModel;

public interface PromotionsRepository extends MongoRepository<PromotionsModel, Integer> {

}
