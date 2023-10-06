package com.wm.ECartPGPTeamTen.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wm.ECartPGPTeamTen.exception.ECartException;
import com.wm.ECartPGPTeamTen.exception.ResourceNotFoundException;
import com.wm.ECartPGPTeamTen.model.UserModel;
import com.wm.ECartPGPTeamTen.service.UserService;
import static com.wm.ECartPGPTeamTen.util.ECartUtil.INTERNAL_ERROR;

/**
 * r0m09yu
 */

@RestController
@RequestMapping("/api/ecartp")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserModel> getEmployeeById(@PathVariable(value = "id") int userId)
			throws ResourceNotFoundException, ECartException {
		logger.info("Fetching user infol for User id :{0}", userId);
		try {
			List<UserModel> listUser = userService.getUserDetails(userId);
			if (listUser != null && listUser.size() > 0) {
				return ResponseEntity.ok().body(listUser.get(0));
			} else {
				logger.error("Fetching user infol for User id :{0}", userId);
				throw new ResourceNotFoundException("Employee not found for this id :: " + userId);
			}
		}catch (Exception e) {
			throw new ECartException(INTERNAL_ERROR+" :: " + userId);
		}
	}

}
