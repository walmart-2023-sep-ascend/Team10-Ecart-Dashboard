package com.wm.ECartPGPTeamTen.dao;

import static com.wm.ECartPGPTeamTen.util.ECartUtil.SPACE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.wm.ECartPGPTeamTen.dao.repository.ProductsRepository;
import com.wm.ECartPGPTeamTen.model.ProductsModel;

@Repository
public class ProductsDao {

	private static final Logger logger = LoggerFactory.getLogger(ProductsDao.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	ProductsRepository productsRepository;

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<ProductsModel> findById(Integer id) {
		logger.debug("Get Details by Id");
		BasicQuery query = new BasicQuery("{ id : { $eq : " + id + " }}");
		return mongoTemplate.find(query, ProductsModel.class);
	}

	/**
	 * 
	 * @param userSearches
	 * @return
	 * @throws Exception
	 */
	public List<ProductsModel> findProducts(String[] userSearches) throws Exception {
		logger.debug("Get product based on user recent searches.....!");

		try {

			List<String> search = new ArrayList<String>();
			if (userSearches != null && userSearches.length > 0) {
				for (String s : userSearches) {
					search.addAll(Arrays.stream(s.split(SPACE)).collect(Collectors.toList()));
				}

				String searchs = search.stream().collect(Collectors.joining("\", \"", "\"", "\""));
				String q = "{ searchTags : { $in : " + Arrays.asList(searchs) + " }}";
				BasicQuery query = new BasicQuery(q);
				return mongoTemplate.find(query, ProductsModel.class);
			}

		} catch (Exception e) {
			logger.error("Error occured " + e);
			throw new Exception("Error occured");
		}
		return null;
	}

	/**
	 * 
	 * @param favCategories
	 * @return
	 * @throws Exception
	 */

	public List<ProductsModel> findFavCatAndBrands(String[] favCategories) throws Exception {
		logger.debug("Get product based on user recent searches.....!");

		try {

			List<String> search = new ArrayList<String>();
			if (favCategories != null && favCategories.length > 0) {
				for (String s : favCategories) {
					search.addAll(Arrays.stream(s.split(SPACE)).collect(Collectors.toList()));
				}

				String searchs = search.stream().collect(Collectors.joining("\", \"", "\"", "\""));
				String q = "{ $or: [{ productCategory : { $in : " + Arrays.asList(searchs) + " }},"
						+ "{ brand : { $in : " + Arrays.asList(searchs) + " }}]}";
				BasicQuery query = new BasicQuery(q);
				query.fields().include("brand", "productCategory");
				return mongoTemplate.find(query, ProductsModel.class);
			}

		} catch (Exception e) {
			logger.error("Error occured " + e);
			throw new Exception("Error occured");
		}
		return null;
	}
}
