package com.wm.ECartPGPTeamTen;

import static com.wm.ECartPGPTeamTen.util.ECartUtil.INTERNAL_ERROR;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;

import com.wm.ECartPGPTeamTen.controller.RecentSearchlistController;
import com.wm.ECartPGPTeamTen.dao.ProductsDao;
import com.wm.ECartPGPTeamTen.dao.UserDao;
import com.wm.ECartPGPTeamTen.dao.repository.ProductsRepository;
import com.wm.ECartPGPTeamTen.exception.ECartException;
import com.wm.ECartPGPTeamTen.model.ProductsModel;
import com.wm.ECartPGPTeamTen.model.UserModel;
import com.wm.ECartPGPTeamTen.service.ProductService;
import com.wm.ECartPGPTeamTen.vo.CategoryAndBrandVO;
import com.wm.ECartPGPTeamTen.vo.ResponseVO;

public class ProductServicesTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(ProductServicesTest.class);

	@Mock
	MongoTemplate mongoTemplate;

	@Mock
	ProductsDao productsDao;

	@InjectMocks
	ProductService productService;

	@Mock
	UserDao userDao;

	@Mock
	ProductsRepository productsRepository;
	
	@InjectMocks
	RecentSearchlistController searchlistController;

	@BeforeEach
	void initialize() {
		logger.info("Before test cases in product test");
		userDao = new UserDao(mongoTemplate);
		productService = new ProductService(productsRepository, mongoTemplate, userDao, productsDao);
		productsDao = new ProductsDao(mongoTemplate);
		searchlistController = new RecentSearchlistController(productService); 
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getProductBasedonRecentSearchTest() throws Exception {
		
		List<UserModel> returnList = new ArrayList<UserModel>();
		UserModel model = new UserModel();
		model.setId(1);
		model.set_id("test");
		model.setRecentSearches(new String[] {"pen", "del"});
		returnList.add(model);
		when(userDao.findUserSearchesById(anyInt())).thenReturn(returnList);
		
		List<ProductsModel> prodList = new ArrayList<ProductsModel>();
		ProductsModel prod = new ProductsModel(1,"pen","cricket");
		prodList.add(prod);
		logger.debug(prod.toString());
		
		when(productsDao.findProducts(any())).thenReturn(prodList);
		
		ResponseEntity<ResponseVO> categoryAndBrandVO = searchlistController.getProductBasedonRecentSearch(1);
		assertTrue(categoryAndBrandVO.getStatusCode().is2xxSuccessful());
	}
	@Test
	public void getProductBasedonRecentSearchEmptyListTest() throws Exception {
		
		List<UserModel> returnList = new ArrayList<UserModel>();
		UserModel model = new UserModel();
		model.setId(1);
		model.set_id("test");
		model.setRecentSearches(new String[] {"pen", "del"});
		returnList.add(model);
		when(userDao.findUserSearchesById(anyInt())).thenReturn(returnList);
		
		List<ProductsModel> prodList = new ArrayList<ProductsModel>();
		when(productsDao.findProducts(any())).thenReturn(prodList);
		
		Throwable exception = assertThrows(ECartException.class, () -> searchlistController.getProductBasedonRecentSearch(1));
		assertEquals(INTERNAL_ERROR + " :: " + 1, exception.getMessage());
		
	}
	
	@Test
	public void getProductBasedonRecentSearchTestEx() throws Exception {
		
		List<UserModel> returnList = new ArrayList<UserModel>();
		UserModel model = new UserModel();
		model.setId(1);
		model.set_id("test");
		model.setRecentSearches(new String[] {"pen", "del"});
		returnList.add(model);
		when(userDao.findUserSearchesById(anyInt())).thenThrow(new Exception("Issue"));
		
		List<ProductsModel> prodList = new ArrayList<ProductsModel>();
		when(productsDao.findProducts(any())).thenReturn(prodList);
		
		Throwable exception = assertThrows(ECartException.class, () -> searchlistController.getProductBasedonRecentSearch(1));
		assertEquals(INTERNAL_ERROR + " :: " + 1, exception.getMessage());
		
	}
	
	@Test
	public void getFavBrandsCategoriesTest() throws Exception {
		
		List<UserModel> returnList = new ArrayList<UserModel>();
		UserModel model = new UserModel();
		model.setId(1);
		model.set_id("test");
		model.setFavoriteCategories(new String[] {"pen", "del"});
		returnList.add(model);
		when(userDao.findUserfavbrandsById(anyInt())).thenReturn(returnList);
		
		List<ProductsModel> prodList = new ArrayList<ProductsModel>();
		ProductsModel prod = new ProductsModel(1,"pen","cricket");
		prodList.add(prod);
		logger.debug(prod.toString());
		
		when(productsDao.findFavCatAndBrands(any())).thenReturn(prodList);
		
		ResponseEntity<ResponseVO> categoryAndBrandVO = searchlistController.getFavBrandsCategories(1);
		assertTrue(categoryAndBrandVO.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	public void getFavBrandsCategoriesTestEmpty() throws Exception {
		
		List<UserModel> returnList = new ArrayList<UserModel>();
		UserModel model = new UserModel();
		model.setId(1);
		model.set_id("test");
		model.setFavoriteCategories(new String[] {"pen", "del"});
		returnList.add(model);
		when(userDao.findUserfavbrandsById(anyInt())).thenReturn(returnList);
		
		List<ProductsModel> prodList = new ArrayList<ProductsModel>();
		when(productsDao.findFavCatAndBrands(any())).thenReturn(prodList);
		
		
		Throwable exception = assertThrows(ECartException.class, () -> searchlistController.getFavBrandsCategories(1));
		assertEquals(INTERNAL_ERROR + " :: " + 1, exception.getMessage());
	}
	
	@Test
	public void getFavBrandsCategoriesTestEx() throws Exception {
		
		List<UserModel> returnList = new ArrayList<UserModel>();
		UserModel model = new UserModel();
		model.setId(1);
		model.set_id("test");
		model.setFavoriteCategories(new String[] {"pen", "del"});
		returnList.add(model);
		when(userDao.findUserfavbrandsById(anyInt())).thenThrow(new Exception("Issue"));
		
		List<ProductsModel> prodList = new ArrayList<ProductsModel>();
		when(productsDao.findFavCatAndBrands(any())).thenReturn(prodList);
		
		
		Throwable exception = assertThrows(ECartException.class, () -> searchlistController.getFavBrandsCategories(1));
		assertEquals(INTERNAL_ERROR + " :: " + 1, exception.getMessage());
	}
	
}
