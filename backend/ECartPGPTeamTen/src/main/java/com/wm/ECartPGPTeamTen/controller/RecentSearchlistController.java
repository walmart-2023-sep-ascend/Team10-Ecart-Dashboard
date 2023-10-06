package com.wm.ECartPGPTeamTen.controller;

import static com.wm.ECartPGPTeamTen.util.ECartUtil.INTERNAL_ERROR;

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
import com.wm.ECartPGPTeamTen.model.ProductsModel;
import com.wm.ECartPGPTeamTen.model.PromotionsModel;
import com.wm.ECartPGPTeamTen.service.ProductService;
import com.wm.ECartPGPTeamTen.service.PromotionsService;
import com.wm.ECartPGPTeamTen.vo.CategoryAndBrandVO;

/**
 * r0m09yu
 */

@RestController
@RequestMapping("/api/ecartp")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecentSearchlistController {

	private static final Logger logger = LoggerFactory.getLogger(RecentSearchlistController.class);

	@Autowired
	ProductService productService;

	@Autowired
	PromotionsService promotionsService;

	/**
	 * Get all the product based on user recent searches
	 * 
	 * @param userId
	 * @return
	 * @throws ResourceNotFoundException
	 * @throws ECartException
	 */
	@RequestMapping(value = "/recentSearches/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductsModel>> getProductBasedonRecentSearch(@PathVariable(value = "id") int userId)
			throws ResourceNotFoundException, ECartException {
		logger.info("Fetching Recent searches for User id :{0}", userId);
		try {
			List<ProductsModel> products = productService.getRecentSearches(userId);
			if (products != null && products.size() > 0) {
				return ResponseEntity.ok().body(products);
			} else {
				logger.error("Fetching user infol for User id :{0}", userId);
				throw new ResourceNotFoundException("No products found for this id :: " + userId);
			}
		} catch (Exception e) {
			throw new ECartException(INTERNAL_ERROR + " :: " + userId);
		}
	}

	/**
	 * Get all favorite brands and categories - bases on user favoriteCategories
	 * 
	 * @param userId
	 * @return
	 * @throws ResourceNotFoundException
	 * @throws ECartException
	 */
	@RequestMapping(value = "/favBrandsCatg/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryAndBrandVO> getFavBrandsCategories(@PathVariable(value = "id") int userId)
			throws ResourceNotFoundException, ECartException {
		logger.info("Fetching Favourite brand and categories detials :{0}", userId);
		try {
			CategoryAndBrandVO catAndBrandDetails = productService.getFavBrandsCategories(userId);
			if (catAndBrandDetails != null) {
				return ResponseEntity.ok().body(catAndBrandDetails);
			} else {
				logger.error("Fetching user infol for User id :{0}", userId);
				throw new ResourceNotFoundException("No products found for this id :: " + userId);
			}
		} catch (Exception e) {
			throw new ECartException(INTERNAL_ERROR + " :: " + userId);
		}
	}

	/**
	 * Getting all the products based on fav category and searches
	 * 
	 * @param userId
	 * @return
	 * @throws ResourceNotFoundException
	 * @throws ECartException
	 */
	@RequestMapping(value = "/favBrandsCatgProducts/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductsModel>> getFavBrandsCategoriesProducts(@PathVariable(value = "id") int userId)
			throws ResourceNotFoundException, ECartException {
		logger.info("Fetching Favourite brand and categories detials :{0}", userId);
		try {
			List<ProductsModel> products = productService.getFavBrandsCategoriesProducts(userId);
			if (products != null && products.size() > 0) {
				return ResponseEntity.ok().body(products);
			} else {
				logger.error("Fetching fav products based on cate id :{0}", userId);
				throw new ResourceNotFoundException("No products found for this id :: " + userId);
			}
		} catch (Exception e) {
			throw new ECartException(INTERNAL_ERROR + " :: " + userId);
		}
	}

	@RequestMapping(value = "/getPromotions", method = RequestMethod.GET)
	public ResponseEntity<List<PromotionsModel>> getActivePromotions()
			throws ResourceNotFoundException, ECartException {
		logger.info("Fetching Active promotinos ");
		try {
			List<PromotionsModel> promotionsList = promotionsService.getActivePromotinos();
			if (promotionsList != null) {
				return ResponseEntity.ok().body(promotionsList);
			} else {
				logger.error("Fetching Active promotinos");
				throw new ResourceNotFoundException("No active promotinos ");
			}
		} catch (Exception e) {
			throw new ECartException(INTERNAL_ERROR + " :: ");
		}
	}
}
