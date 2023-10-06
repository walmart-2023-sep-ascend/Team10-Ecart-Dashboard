package com.wm.ECartPGPTeamTen.service;

import static com.wm.ECartPGPTeamTen.util.ECartUtil.INTERNAL_ERROR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.ECartPGPTeamTen.dao.ProductsDao;
import com.wm.ECartPGPTeamTen.dao.UserDao;
import com.wm.ECartPGPTeamTen.exception.ECartException;
import com.wm.ECartPGPTeamTen.model.ProductsModel;
import com.wm.ECartPGPTeamTen.model.UserModel;
import com.wm.ECartPGPTeamTen.vo.CategoryAndBrandVO;

/**
 * r0m09yu
 */

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	UserDao userDao;

	@Autowired
	ProductsDao productsDao;

	/**
	 * Fetches recent search products.
	 * 
	 * @param userId
	 * @return
	 * @throws ECartException
	 */
	public List<ProductsModel> getRecentSearches(int userId) throws ECartException {
		List<ProductsModel> recentSearchList = new ArrayList<ProductsModel>();
		try {
			List<UserModel> list = userDao.findUserSearchesById(userId);
			logger.info("User details :list {0}" + list);
			if (list != null && list.size() > 0) {
				String[] arr = list.get(0).getRecentSearches();
				recentSearchList = productsDao.findProducts(arr);

				return recentSearchList;
			}
			return recentSearchList;
		} catch (Exception e) {
			logger.error("Error occured " + e);
			throw new ECartException(INTERNAL_ERROR + " :: " + userId);
		}
	}

	/**
	 * Fetches fav brands and categories
	 * 
	 * @param userId
	 * @return
	 * @throws ECartException
	 */
	public CategoryAndBrandVO getFavBrandsCategories(int userId) throws ECartException {
		try {
			CategoryAndBrandVO vo = new CategoryAndBrandVO();
			List<UserModel> list = userDao.findUserfavbrandsById(userId);
			logger.info("User details :list {0}" + list);
			if (list != null && list.size() > 0) {
				String[] arr = list.get(0).getFavoriteCategories();
				List<ProductsModel> prodList = productsDao.findFavCatAndBrands(arr);
				logger.debug("After fetching product detail");
				if (prodList != null && prodList.size() > 0) {
					List<String> brandsList = prodList.stream().map(prod -> prod.getBrand())
							.collect(Collectors.toList());
					List<String> catList = prodList.stream().map(prod -> prod.getProductCategory())
							.collect(Collectors.toList());
					vo.setBrands(brandsList);
					vo.setCategories(catList);
				}
				return vo;
			}

			return vo;
		} catch (Exception e) {
			logger.error("Error occured " + e);
			throw new ECartException(INTERNAL_ERROR + " :: " + userId);
		}
	}

	/**
	 * Get all fav products based on fav category And Search List as well
	 * @param userId
	 * @return
	 * @throws ECartException
	 */
	public List<ProductsModel> getFavBrandsCategoriesProducts(int userId) throws ECartException {
		List<ProductsModel> prodList = new ArrayList<ProductsModel>();
		try {
			List<String> search = new ArrayList<String>();
			
			List<UserModel> favCatlist = userDao.findUserfavbrandsById(userId);
			List<UserModel> searchList = userDao.findUserSearchesById(userId);
			logger.info("User details :list {0}" + favCatlist);
			logger.info("User details :list {0}" + searchList);
			
			if (searchList != null && searchList.size() > 0) {
				String[] arr = searchList.get(0).getRecentSearches();
				search.addAll(Arrays.asList(arr));
			}
			if (favCatlist != null && favCatlist.size() > 0) {
				String[] arr = favCatlist.get(0).getFavoriteCategories();
				search.addAll(Arrays.asList(arr));
			}
			
			if (search != null && search.size() > 0) {
				String sear[] = new String[search.size()];
				sear = search.toArray(sear);
				prodList = productsDao.findFavCatAndBrands(sear);
				logger.debug("After fetching product detail");
				if (prodList != null && prodList.size() > 0) {
					return prodList;
				}
				return prodList;
			}

			return prodList;
		} catch (Exception e) {
			logger.error("Error occured " + e);
			throw new ECartException(INTERNAL_ERROR + " :: " + userId);
		}
	}
}
