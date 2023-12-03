package com.ascend.userdashboard.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ascend.userdashboard.model.Products;

@Repository
public interface ProductRepository {

	public List<Products> getProducts(String query,String path);

	List<Products> getProductsById(int value);
}
