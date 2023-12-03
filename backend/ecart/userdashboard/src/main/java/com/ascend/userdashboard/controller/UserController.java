package com.ascend.userdashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ascend.userdashboard.model.ModifiedProduct;
import com.ascend.userdashboard.model.Products;
import com.ascend.userdashboard.model.WishlistProducts;
import com.ascend.userdashboard.repository.UserRepository;
import com.ascend.userdashboard.service.UserDashboardService;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/userdashboard")
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	UserDashboardService userDashSer;
	
	@GetMapping("getUserSearches/{userId}")
	public List<String> getUserSearches(@PathVariable Integer userId){
		List<String> userSearches = new ArrayList<String>();
		userSearches = userDashSer.getUserSearches(userId);
		StringBuilder searchBuilder = new StringBuilder();
		userSearches.forEach(search -> searchBuilder.append(search).append(","));
	List<ModifiedProduct>  products = userDashSer.getProducts(searchBuilder.toString(),"productCategory");
//		return userSearches;
		return userSearches;
	}
	
	
	@GetMapping("getUserInterests/{userId}")
	public List<String> getUserInterests(@PathVariable Integer userId){
		List<String> userInterests = new ArrayList<String>();
		userInterests = userDashSer.getUserInterests(userId);
		return userInterests;
	}
	
	@GetMapping("getUserFavCategories/{userId}")
	public List<String> getUserFavCategories(@PathVariable Integer userId){
		List<String> userInterests = new ArrayList<String>();
		userInterests = userDashSer.getUserFavCategories(userId);
		return userInterests;
	}
	
	@GetMapping("getUserWishlist/{userEmail}")
	public List<ModifiedProduct> getUserWishlist(@PathVariable String userEmail){
		List<String> productIdList = userDashSer.getUserWishlist(userEmail);
		int i =0;
		List<ModifiedProduct> prodNameList = new ArrayList<ModifiedProduct>();
		for(String s : productIdList) {
			List<Products> prods = userDashSer.getProductsById(Integer.parseInt(s));
			for(Products p : prods) {
				ModifiedProduct mp = new ModifiedProduct();
				mp.setTitle(p.getTitle());
				mp.setId(i++);
				prodNameList.add(mp);
			}
		}
		return prodNameList;
	}
	
	@GetMapping("getUserProducts/{userId}")
	public List<ModifiedProduct> getProductsFromUserSearches(@PathVariable Integer userId){
		List<String> userSearches = new ArrayList<String>();
		userSearches = userDashSer.getUserSearches(userId);
		StringBuilder searchBuilder = new StringBuilder();
		userSearches.forEach(search -> searchBuilder.append(search).append(","));
	List<ModifiedProduct>  products = getProducts(searchBuilder.toString(),"productCategory");
//		return userSearches;
		return products;
	}
	private List<ModifiedProduct> getProducts(String query,String path){
		List<ModifiedProduct>  products = userDashSer.getProducts(query,path);
		return products;
	}
//	
//	@GetMapping("/getUsersCount")
//	public String getUsers() {
//		System.out.println("Count is "+ userRepo.count());
//		return "Count of users is "+Long.toString(userRepo.count());
//	}
//	
//	public static String toHexString(byte[] ba) {
//	    StringBuilder str = new StringBuilder();
//	    for(int i = 0; i < ba.length; i++)
//	        str.append(String.format("%x", ba[i]));
//	    return str.toString();
//	}
	
//	@GetMapping("/findSearchesById/{id}")
//	public String findSearchesById(@PathVariable String id) {
//		System.out.println("Searches for " + id );
//		//ObjectId obj = ObjectId.Parse(id);
//		System.out.println("Searches for " + id + " is "+ userRepo.findById(toHexString(id.getBytes())));
//		return "Searches for " + id + " is "+ userRepo.findById(toHexString(id.getBytes()).toString());
//	}
	
	
//	@GetMapping("/findSearchesById/{id}")
//	public String findSearchesById(@PathVariable int id) {
//		System.out.println("Searches for " + id );
//		System.out.println("Searches for " + id + " is "+ userRepo.findById(id));
//		return "Searches for " + id + " is "+ userRepo.findById(id);
//	}
//	
//	
//	@GetMapping("/findRecentSearchesByGender/{gender}")
//	public String findRecentSearchesByGender(@PathVariable String gender) {
//		List<User> genderList = userRepo.findRecentSearchesByGender(gender);
//		System.out.println("Searches for " + gender + " is "+ genderList);
//		for(int i = 0; i<genderList.size();i++) {
//			System.out.println("user = "+ genderList.get(i));
//		}
//		return genderList.toString();
//	}
//	
//	@GetMapping("/findAll")
//	public List<User> findAll() {
//		System.out.println("Searches for " + " is "+ userRepo.findAll());
//		return userRepo.findAll();
//	}
	

	
	
//	@GetMapping("getUserSearches/{userId}")
//	public List<Products> getUserSearches(@PathVariable Integer userId){
//		List<String> userSearches = new ArrayList<String>();
//		userSearches = userDashSer.getUserSearches(userId);
//		StringBuilder searchBuilder = new StringBuilder();
//		userSearches.forEach(search -> searchBuilder.append(search).append(","));
//		System.out.println("searchBuilder.toString()-------------->"+searchBuilder.toString());
//		List<Products> products = userDashSer.getProducts(searchBuilder.toString());
//		return products;
//	}
}
