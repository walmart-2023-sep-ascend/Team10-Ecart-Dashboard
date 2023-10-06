package com.wm.ECartPGPTeamTen.dao.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wm.ECartPGPTeamTen.model.ProductsModel;

@Repository
public interface ProductsRepository extends MongoRepository<ProductsModel, Integer> {

	
	public List<ProductsModel> findBySearchTags(String[] tags);
	
	public List<ProductsModel> findBySearchTags(String tags);
}
