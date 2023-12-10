package com.wm.userdashboard.service;

import static com.wm.userdashboard.util.ECartUtil.INTERNAL_ERROR;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.userdashboard.exception.ECartException;
import com.wm.userdashboard.exception.ResourceNotFoundException;
import com.wm.userdashboard.model.UserModel;
import com.wm.userdashboard.repository.UserRepository;

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

//	@Autowired
//	UserDao userDao;
//	
//	public UserService(UserRepository userRepository,MongoTemplate mongoTemplate) {
//		this.userRepository = userRepository;
//		this.userDao = new UserDao(mongoTemplate);
//	}
//	
//	public List<UserModel> getUserDetails(int userId) throws ECartException {
//		try {
//			List<UserModel> list = userDao.findById(userId);
//			//list = userDao.findUserSearchesById(userId);
//			logger.info("User details :list {0}" + list);
//			return list;
//		} catch (Exception e) {
//			logger.error("Error occured " + e); 
//			throw new ECartException(INTERNAL_ERROR + " :: " + userId);
//		}
//
//	}
//
//	public Optional<UserModel> getUserDetailsEmail(String email) throws ResourceNotFoundException, ECartException {
//		try {
//			Optional<UserModel> user = userRepository.findByEmail(email);
//			logger.info("User details :", user.get());
//			return user;
//		} catch (Exception e) {
//			logger.error("Error occured :" + email);
//			throw new ECartException(INTERNAL_ERROR + " :: " + email);
//		}
//	}
	public Optional<UserModel> findByEmail(String emailId) throws ResourceNotFoundException, ECartException {
		try {
		return userRepository.findByEmail(emailId);
	} catch (Exception e) {
		logger.error("Error occured :" + emailId);
		throw new ECartException(INTERNAL_ERROR + " : " + emailId);
	}
	}

}
