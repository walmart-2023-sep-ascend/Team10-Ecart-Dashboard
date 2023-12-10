package com.wm.userdashboard.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wm.userdashboard.model.UserModel;

/**
 * s0k05sw
 */

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

	Optional<UserModel> findByEmail(String email) throws Exception;
}
