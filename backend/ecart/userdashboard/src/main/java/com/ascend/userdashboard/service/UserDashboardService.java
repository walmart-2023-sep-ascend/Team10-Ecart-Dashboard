package com.ascend.userdashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ascend.userdashboard.model.ModifiedProduct;
import com.ascend.userdashboard.model.Products;
import com.ascend.userdashboard.model.User;
import com.ascend.userdashboard.model.Wishlist;
import com.ascend.userdashboard.model.WishlistProducts;
import com.ascend.userdashboard.repository.ProductRepository;
import com.ascend.userdashboard.repository.SearchRepository;
import com.ascend.userdashboard.repository.WishlistRepository;

@Service
public class UserDashboardService {

	@Autowired
	SearchRepository sRepo;
	
	@Autowired
	ProductRepository pRepo;
	
	public List<String> getUserSearches(@PathVariable Integer userId){
		List<String> userSearches = new ArrayList<String>();
		for(User user : sRepo.getUserSearches(userId)) {
			System.out.println(user.getId()+ " -----" + user.recentSearches.toString());
		}
		List<User> users = sRepo.getUserSearches(userId);
		if(users.size()==1) {
			userSearches = users.get(0).getRecentSearches();
		} else if (users.size()>1) {
			System.out.println("Duplicate user id s found");
		}else {
			System.out.println("User id not found");
		}
		return userSearches;
	}
	
	
	
	public List<String> getUserInterests(@PathVariable Integer userId){
		List<String> userSearches = new ArrayList<String>();
		for(User user : sRepo.getUserSearches(userId)) {
			System.out.println(user.getId()+ " -----" + user.recentSearches.toString());
		}
		List<User> users = sRepo.getUserSearches(userId);
		if(users.size()==1) {
			userSearches = users.get(0).getInterests();
		} else if (users.size()>1) {
			System.out.println("Duplicate user id s found");
		}else {
			System.out.println("User id not found");
		}
		return userSearches;
	}
	public List<String> getUserFavCategories(@PathVariable Integer userId){
		List<String> userSearches = new ArrayList<String>();
		for(User user : sRepo.getUserSearches(userId)) {
			System.out.println(user.getId()+ " -----" + user.recentSearches.toString());
		}
		List<User> users = sRepo.getUserSearches(userId);
		if(users.size()==1) {
			userSearches = users.get(0).getFavoriteCategories();
		} else if (users.size()>1) {
			System.out.println("Duplicate user id s found");
		}else {
			System.out.println("User id not found");
		}
		return userSearches;
	}
	public List<ModifiedProduct> getProducts( String searchText, String path){
		System.out.println("Inside product method");
//		List<Products> products = pRepo.getProducts(searchText);
//		List<Product> product = products.get(0).getProducts();
		List<Products> products = pRepo.getProducts(searchText,path);
		System.out.println("Products size = " + products.size());
		List<ModifiedProduct> modifiedProducts = new ArrayList<ModifiedProduct>();
		int i=0;
		for(Products prod : products ) {
//			System.out.println(i++);
			System.out.println(prod.getShortDescription());
			System.out.println(prod.getProductCategory());
			System.out.println(prod.getImageUrls());
			System.out.println(prod.getId());
			ModifiedProduct product = new ModifiedProduct();
			product.setId(i++);
			product.setTitle(prod.getTitle());
			product.setDiscount(prod.getDiscount());
			product.setShortDescription(prod.getShortDescription());
			String imageUrl = (prod.getImageUrls().size()>0? prod.getImageUrls().get(0):"");
			product.setImageUrls(imageUrl);
			modifiedProducts.add(product);
		}
		
//		Gson gson = new Gson();
//	    String jsonArray = gson.toJson(modifiedProducts);
	    
		return modifiedProducts;
	}

	@Autowired
	WishlistRepository wishlistRepo;
	public List<String> getUserWishlist(String userEmail) {
		List<Wishlist> wishlistList = wishlistRepo.getUserWishlist(userEmail);
		List<String> userWishlistProducts = new ArrayList();
		for(Wishlist w : wishlistList) {
			List<WishlistProducts> wp = w.getProducts();
			for(WishlistProducts wlp: wp) {
				userWishlistProducts.add(wlp.getProductId());
			}
		}
		
		return userWishlistProducts;
	}

	public List<Products> getProductsById(int value) {
		List<Products> prodList = pRepo.getProductsById(value);
		return prodList;
	}
}
