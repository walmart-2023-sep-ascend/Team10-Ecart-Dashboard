package com.wm.userdashboard.service;

import static com.wm.userdashboard.util.ECartUtil.INTERNAL_ERROR;

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
 * r0m09yu
 */

@Service
@NoArgsConstructor
public class WishlistService {

	private static final Logger logger = LoggerFactory.getLogger(WishlistService.class);

	@Autowired
	WishlistRepository wishlistRepository;

	
	
	public Optional<WishListModel> getUserWishlist(String userEmail) throws ResourceNotFoundException, ECartException {
		try {
		Optional<WishListModel> wishlistList = wishlistRepository.findByUserId(userEmail);
		
		
		return wishlistList;
		} catch (Exception e) {
			logger.error("Error occured :" + userEmail);
			throw new ECartException(INTERNAL_ERROR + " : " + userEmail);
		}
		}

}
