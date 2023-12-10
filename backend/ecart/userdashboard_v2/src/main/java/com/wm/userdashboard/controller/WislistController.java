package com.wm.userdashboard.controller;

import static com.wm.userdashboard.util.ECartUtil.INTERNAL_ERROR;
import static com.wm.userdashboard.util.ECartUtil.RECORD_NOT_FOUND;

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
 * s0k05sw
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WislistController {

	private static final Logger logger = LoggerFactory.getLogger(WislistController.class);

	@Autowired
	WishlistService wishlistService;
	
	
	@GetMapping("user/wishlist/{userEmail}")
	public ResponseEntity<List<String>> getUserWishlist(@PathVariable String userEmail) throws ResourceNotFoundException, ECartException {{
	    
	    try {
	        

	        Optional<WishListModel> wishlist = wishlistService.getUserWishlist(userEmail);
			List<String> productIdList = new ArrayList<>();
			if(wishlist.isPresent()) {
			 List<ProductsWishListModel> products = wishlist.get().getProducts();
			 
			for(int counter =0 ; counter<products.size();counter++) {
				if(products.get(counter).getInventoryStatus().equals("available")) {
					productIdList.add(products.get(counter).getProductId());
				}
			}
			return ResponseEntity.ok(productIdList);
			 } else {
		            // If the user with the provided email ID does not exist, return a 404 Not Found status code
		            
		            logger.error("Fetching wishlist for email :{0}", userEmail);
					throw new ResourceNotFoundException(RECORD_NOT_FOUND + " : " + userEmail);
		        }
		    } catch (Exception e) {
		        // Handle unexpected errors and return a 500 Internal Server Error status code
		    	throw new ECartException(INTERNAL_ERROR + " : " + userEmail);
		    }
	}
	
	
	}
	
	
}
