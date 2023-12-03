package com.ascend.userdashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ascend.userdashboard.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
	    
//	    @Query("{name:'?0'}")
//	    User findUserByName(String name);
	    
//	    @Query(value="{id:'?0'}", fields="{'name' : 1, 'gender' : 1}")
	    List<User> findAll();
	    
	    public long count();

//	    @Query(value="{id:'?0'}")
	  //  @Query("{id:'?0'}")
	   //@Query(value = "{ 'id' : ?0 }")	    
	    public Optional<User> findById(int id);

	    
	    
	    @Query(value="{gender:'?0'}",fields="{'recentSearches' : 1}")
	    List<User> findRecentSearchesByGender(String gender);
	}

