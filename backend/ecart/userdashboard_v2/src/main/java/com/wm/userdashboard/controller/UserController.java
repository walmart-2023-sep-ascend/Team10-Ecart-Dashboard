package com.wm.userdashboard.controller;

import static com.wm.userdashboard.util.ECartUtil.INTERNAL_ERROR;
import static com.wm.userdashboard.util.ECartUtil.RECORD_NOT_FOUND;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.userdashboard.exception.ECartException;
import com.wm.userdashboard.exception.ResourceNotFoundException;
import com.wm.userdashboard.model.UserModel;
import com.wm.userdashboard.service.UserService;
import com.wm.userdashboard.vo.UserDataResponseVO;;

/**
 * s0k05sw
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	

	@GetMapping("user/{emailId}")
	public ResponseEntity<UserDataResponseVO> getUserData(@PathVariable String emailId) throws ResourceNotFoundException, ECartException {{
		
		try {
	        
	    	logger.info("Fetching user information for email id : {0}", emailId);
	        // Retrieve user data based on the email ID
	        Optional<UserModel> userModel = userService.findByEmail(emailId);

	        // Check if the user with the provided email ID exists
	        if (userModel.isPresent()) {
	        	UserModel user = userModel.get();
	        
	            // If the user exists, retrieve and process user data
	            List<String> userInterests = user.getInterests();
	            List<String> favCategories = user.getFavoriteCategories();
                List<String> recentSearches = user.getRecentSearches();

	            
	            UserDataResponseVO userDataResponse = new UserDataResponseVO(userInterests, favCategories, recentSearches);

	            // Return the response along with a 200 OK status code
	            
	            return ResponseEntity.ok(userDataResponse);
	        } else {
	            // If the user with the provided email ID does not exist, return a 404 Not Found status code
	            
	            logger.error("Fetching user info for email :{0}", emailId);
				throw new ResourceNotFoundException(RECORD_NOT_FOUND + " : " + emailId);
	        }
	    } catch (Exception e) {
	        // Handle unexpected errors and return a 500 Internal Server Error status code
	    	throw new ECartException(INTERNAL_ERROR + " : " + emailId);
	    }
	}

	
	}
	
}
