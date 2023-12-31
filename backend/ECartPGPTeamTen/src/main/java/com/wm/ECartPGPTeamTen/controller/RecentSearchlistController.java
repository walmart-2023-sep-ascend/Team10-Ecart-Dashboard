package com.wm.ECartPGPTeamTen.controller;

import static com.wm.ECartPGPTeamTen.util.ECartUtil.INTERNAL_ERROR;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.wm.ECartPGPTeamTen.vo.ResponseVO;

/**
 * r0m09yu
 */

@RestController
@RequestMapping("/api/ecartp")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecentSearchlistController {

	private static final Logger logger = LoggerFactory.getLogger(RecentSearchlistController.class);

	@Autowired
	ProductService productService;

	@Autowired
	PromotionsService promotionsService;

	/**
	 * @param productService
	 */
	public RecentSearchlistController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Get all the product based on user recent searches
	 * 
	 * @param userId
	 * @return
	 * @throws ResourceNotFoundException
	 * @throws ECartException
	 */
	@RequestMapping(value = "/recentSearches/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO> getProductBasedonRecentSearch(@PathVariable(value = "id") int userId)
			throws ResourceNotFoundException, ECartException {
		logger.info("Fetching Recent searches for User id :{0}", userId);
		ResponseVO vo = new ResponseVO();
		try {
			List<ProductsModel> products = productService.getRecentSearches(userId);
			if (products != null && products.size() > 0) {

				vo.setBody(products);
				vo.setCode(HttpStatus.OK.value());
				vo.setMessage("SUCCESS");
				return ResponseEntity.ok().body(vo);
				// return ResponseEntity.ok().body(products);
			} else {
				logger.error("Fetching user infol for User id :{0}", userId);
				vo.setBody(products);
				vo.setCode(HttpStatus.NOT_FOUND.value());
				vo.setMessage("No products found for this id :: " + userId);
				return ResponseEntity.ok().body(vo);
				// throw new ResourceNotFoundException("No products found for this id :: " +
				// userId);
			}
		} catch (Exception e) {
			vo.setBody(e.getMessage());
			vo.setCode(HttpStatus.BAD_GATEWAY.value());
			vo.setMessage(INTERNAL_ERROR);
			return ResponseEntity.ok().body(vo);
			// throw new ECartException(INTERNAL_ERROR + " :: " + userId);
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
	public ResponseEntity<ResponseVO> getFavBrandsCategories(@PathVariable(value = "id") int userId)
			throws ResourceNotFoundException, ECartException {
		logger.info("Fetching Favourite brand and categories detials :{0}", userId);
		ResponseVO vo = new ResponseVO();
		try {
			CategoryAndBrandVO catAndBrandDetails = productService.getFavBrandsCategories(userId);
			if (catAndBrandDetails != null) {
				vo.setBody(catAndBrandDetails);
				vo.setCode(HttpStatus.OK.value());
				vo.setMessage("SUCCESS");
				return ResponseEntity.ok().body(vo);
			} else {
				logger.error("Fetching user infol for User id :{0}", userId);
				vo.setBody(catAndBrandDetails);
				vo.setCode(HttpStatus.NOT_FOUND.value());
				vo.setMessage("No products found for this id :: " + userId);
				return ResponseEntity.ok().body(vo);
				// throw new ResourceNotFoundException("No products found for this id :: " +
				// userId);
			}
		} catch (Exception e) {
			vo.setBody(e.getMessage());
			vo.setCode(HttpStatus.BAD_GATEWAY.value());
			vo.setMessage(INTERNAL_ERROR);
			return ResponseEntity.ok().body(vo);
			// throw new ECartException(INTERNAL_ERROR + " :: " + userId);
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
	public ResponseEntity<ResponseVO> getFavBrandsCategoriesProducts(@PathVariable(value = "id") int userId)
			throws ResourceNotFoundException, ECartException {
		logger.info("Fetching Favourite brand and categories detials :{0}", userId);
		ResponseVO vo = new ResponseVO();
		try {
			List<ProductsModel> products = productService.getFavBrandsCategoriesProducts(userId);
			if (products != null && products.size() > 0) {
				vo.setBody(products);
				vo.setCode(HttpStatus.OK.value());
				vo.setMessage("SUCCESS");
				return ResponseEntity.ok().body(vo);
				// return ResponseEntity.ok().body(products);
			} else {
				logger.error("Fetching fav products based on cate id :{0}", userId);
				vo.setBody(products);
				vo.setCode(HttpStatus.NOT_FOUND.value());
				vo.setMessage("No products found for this id :: " + userId);
				return ResponseEntity.ok().body(vo);
				// throw new ResourceNotFoundException("No products found for this id :: " +
				// userId);
			}
		} catch (Exception e) {
			vo.setBody(e.getMessage());
			vo.setCode(HttpStatus.BAD_GATEWAY.value());
			vo.setMessage(INTERNAL_ERROR);
			return ResponseEntity.ok().body(vo);
			// throw new ECartException(INTERNAL_ERROR + " :: " + userId);
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
