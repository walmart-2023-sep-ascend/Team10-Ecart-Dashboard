package com.wm.userdashboard.service;

import static com.wm.userdashboard.util.UserDashboardUtil.INTERNAL_ERROR;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.userdashboard.exception.ECartException;
import com.wm.userdashboard.exception.ResourceNotFoundException;
import com.wm.userdashboard.model.WishListModel;
import com.wm.userdashboard.repository.WishlistRepository;

import lombok.NoArgsConstructor;

/**
 * Service class for handling wishlist-related operations.
 * 
 * This class interacts with the WishlistRepository to fetch a user's wishlist.
 * 
 * r0m09yu
 */
@Service
@NoArgsConstructor
public class WishlistService {

    private static final Logger logger = LoggerFactory.getLogger(WishlistService.class);

    @Autowired
    private WishlistRepository wishlistRepository;

    /**
     * Retrieves the wishlist for a user based on the provided email ID.
     * 
     * @param userEmail The email ID of the user.
     * @return Optional containing WishListModel if the wishlist is found.
     * @throws ResourceNotFoundException if the user's wishlist is not found.
     * @throws ECartException           if an unexpected error occurs.
     */
    public Optional<WishListModel> getUserWishlist(String userEmail)
            throws ResourceNotFoundException, ECartException {
        try {
            // Log the start of the method
            logger.info("Fetching wishlist for user with email: {}", userEmail);

            // Retrieve user's wishlist based on the email ID
            Optional<WishListModel> wishlistList = wishlistRepository.findByUserId(userEmail);

            // Check if the user's wishlist is present
            if (wishlistList.isPresent()) {
                // Log success message
                logger.info("Wishlist fetched successfully for user with email: {}", userEmail);
            } else {
                // Log a warning if the wishlist is not found
                logger.warn("Wishlist not found for user with email: {}", userEmail);
                throw new ResourceNotFoundException("Wishlist not found for user with email: " + userEmail);
            }

            return wishlistList;
        } catch (Exception e) {
            // Log error message and throw exception
            logger.error("Error occurred while fetching wishlist for user with email: {}", userEmail, e);
            throw new ECartException(INTERNAL_ERROR + " : " + userEmail);
        }
    }
}
