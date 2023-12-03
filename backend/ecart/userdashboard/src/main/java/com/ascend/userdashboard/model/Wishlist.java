package com.ascend.userdashboard.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("wishlist")
public class Wishlist {

	@Id
	private org.bson.types.ObjectId wishListId;
	private String userId;
	private List<WishlistProducts> products;
	public org.bson.types.ObjectId getWishListId() {
		return wishListId;
	}
	public void setWishListId(org.bson.types.ObjectId wishListId) {
		this.wishListId = wishListId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<WishlistProducts> getProducts() {
		return products;
	}
	public void setProducts(List<WishlistProducts> products) {
		this.products = products;
	}
	
}
