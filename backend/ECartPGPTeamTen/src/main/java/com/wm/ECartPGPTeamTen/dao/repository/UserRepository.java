package com.wm.ECartPGPTeamTen.dao.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wm.ECartPGPTeamTen.model.UserModel;

/**
 * r0m09yu
 */

@Repository
public interface UserRepository extends MongoRepository<UserModel, Integer> {

	Optional<UserModel> findByEmail(String email);

}
