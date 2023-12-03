package com.ascend.userdashboard.repository;

import java.util.List;

import com.ascend.userdashboard.model.Wishlist;

public interface WishlistRepository {
	public List<Wishlist> getUserWishlist(String userEmail);
}
