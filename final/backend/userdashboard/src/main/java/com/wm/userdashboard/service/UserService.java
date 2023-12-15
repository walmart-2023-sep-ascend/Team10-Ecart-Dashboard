package com.wm.userdashboard.service;

import static com.wm.userdashboard.util.UserDashboardUtil.INTERNAL_ERROR;

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
 * Service class for handling user-related operations.
 * 
 * This class interacts with the UserRepository to fetch user information.
 * 
 * @author s0k05sw
 */
@Service
@NoArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves user information based on the provided email ID.
     * 
     * @param emailId The email ID of the user.
     * @return Optional containing UserModel if the user is found.
     * @throws ResourceNotFoundException if the user is not found.
     * @throws ECartException           if an unexpected error occurs.
     */
    public Optional<UserModel> findByEmail(String emailId) throws ResourceNotFoundException, ECartException {
        try {
            // Log the start of the method
            logger.info("Fetching user information for email id: {}", emailId);

            // Retrieve user data based on the email ID
            Optional<UserModel> userModel = userRepository.findByEmail(emailId);

            // Check if the user with the provided email ID exists
            if (userModel.isPresent()) {
                // Log success message
                logger.info("User information fetched successfully for email id: {}", emailId);
            } else {
                // Log a warning if the user is not found
                logger.warn("User not found for email id: {}", emailId);
                throw new ResourceNotFoundException("User not found for email id: " + emailId);
            }

            return userModel;
        } catch (Exception e) {
            // Log error message and throw exception
            logger.error("Error occurred while fetching user information for email id: {}", emailId, e);
            throw new ECartException(INTERNAL_ERROR + " : " + emailId);
        }
    }
}
