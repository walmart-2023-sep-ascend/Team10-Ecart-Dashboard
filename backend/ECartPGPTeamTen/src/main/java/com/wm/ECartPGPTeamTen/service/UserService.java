package com.wm.ECartPGPTeamTen.service;

import static com.wm.ECartPGPTeamTen.util.ECartUtil.INTERNAL_ERROR;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.wm.ECartPGPTeamTen.dao.UserDao;
import com.wm.ECartPGPTeamTen.dao.repository.UserRepository;
import com.wm.ECartPGPTeamTen.exception.ECartException;
import com.wm.ECartPGPTeamTen.exception.ResourceNotFoundException;
import com.wm.ECartPGPTeamTen.model.UserModel;

import lombok.NoArgsConstructor;

/**
 * r0m09yu
 */

@Service
@NoArgsConstructor
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDao userDao;
	
	public UserService(UserRepository userRepository,MongoTemplate mongoTemplate) {
		this.userRepository = userRepository;
		this.userDao = new UserDao(mongoTemplate);
	}
	
	public List<UserModel> getUserDetails(int userId) throws ECartException {
		try {
			List<UserModel> list = userDao.findById(userId);
			//list = userDao.findUserSearchesById(userId);
			logger.info("User details :list {0}" + list);
			return list;
		} catch (Exception e) {
			logger.error("Error occured " + e); 
			throw new ECartException(INTERNAL_ERROR + " :: " + userId);
		}

	}

	public Optional<UserModel> getUserDetailsEmail(String email) throws ResourceNotFoundException, ECartException {
		try {
			Optional<UserModel> user = userRepository.findByEmail(email);
			logger.info("User details :", user.get());
			return user;
		} catch (Exception e) {
			logger.error("Error occured :" + email);
			throw new ECartException(INTERNAL_ERROR + " :: " + email);
		}
	}

}
