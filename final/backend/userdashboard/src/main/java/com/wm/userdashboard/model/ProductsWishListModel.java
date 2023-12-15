package com.wm.userdashboard.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * @author Sowmyalakshmi
 */
@Data
@Setter
@Getter
public class ProductsWishListModel{
	private String productId;
	private String inventoryStatus;
	public ProductsWishListModel(String productId, String inventoryStatus) {
		super();
		this.productId = productId;
		this.inventoryStatus = inventoryStatus;
	}
	
}
