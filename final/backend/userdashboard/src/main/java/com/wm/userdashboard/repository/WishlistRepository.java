package com.wm.userdashboard.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.wm.userdashboard.model.WishListModel;
/**
 * @author Sowmyalakshmi
 */
@Repository
public interface WishlistRepository extends MongoRepository<WishListModel, Integer> {
   
	    Optional<WishListModel> findByUserId(String email) ;

	    
	    @Query("{ 'userId' : ?0, 'products.inventoryStatus' : 'available' }")
	    Optional<WishListModel> findByUserIdAndProductAvailability(String email);
	}

