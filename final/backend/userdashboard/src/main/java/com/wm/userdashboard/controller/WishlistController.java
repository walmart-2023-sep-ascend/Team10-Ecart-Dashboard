package com.wm.userdashboard.controller;

import static com.wm.userdashboard.util.UserDashboardUtil.INTERNAL_ERROR;
import static com.wm.userdashboard.util.UserDashboardUtil.RECORD_NOT_FOUND;

import java.util.ArrayList;
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
import com.wm.userdashboard.model.ProductsWishListModel;
import com.wm.userdashboard.model.WishListModel;
import com.wm.userdashboard.service.WishlistService;

/**
 * Controller class for handling wishlist-related operations.
 * 
 * Sowmyalakshmi
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WishlistController {

    private static final Logger logger = LoggerFactory.getLogger(WishlistController.class);

    @Autowired
    private WishlistService wishlistService;

    /**
     * Retrieves the wishlist for a user based on the provided email ID.
     * 
     * @param userEmail The email ID of the user.
     * @return ResponseEntity containing a list of product IDs in the wishlist if the
     *         user exists.
     * @throws ResourceNotFoundException if the user's wishlist is not found.
     * @throws ECartException           if an unexpected error occurs.
     */
    @GetMapping("user/wishlist/{userEmail}")
    public ResponseEntity<List<String>> getUserWishlist(@PathVariable String userEmail)
            throws ResourceNotFoundException, ECartException {
        try {
            // Log the start of the method
            logger.info("Fetching wishlist for user with email: {}", userEmail);

            // Retrieve user's wishlist based on the email ID
            Optional<WishListModel> wishlist = wishlistService.getUserWishlist(userEmail);
            List<String> productIdList = new ArrayList<>();

            // Check if the user's wishlist is present
            if (wishlist.isPresent()) {
                List<ProductsWishListModel> products = wishlist.get().getProducts();

                // Iterate through the products in the wishlist
                for (int counter = 0; counter < products.size(); counter++) {
                    // Check if the product is available
                    if (products.get(counter).getInventoryStatus().equals("available")) {
                        productIdList.add(products.get(counter).getProductId());
                    }
                }
                // Return the product IDs with a 200 OK status code
                return ResponseEntity.ok(productIdList);
            } else {
                // If the user with the provided email ID does not exist, return a 404 Not Found status code
                logger.error("Fetching wishlist for email: {}", userEmail);
                throw new ResourceNotFoundException(RECORD_NOT_FOUND + " : " + userEmail);
            }
        } catch (Exception e) {
            // Handle unexpected errors and return a 500 Internal Server Error status code
            logger.error("Error while fetching wishlist for user with email: {}", userEmail, e);
            throw new ECartException(INTERNAL_ERROR + " : " + userEmail);
        }
    }
}
