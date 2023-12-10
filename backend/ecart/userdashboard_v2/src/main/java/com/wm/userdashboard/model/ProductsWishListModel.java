package com.wm.userdashboard.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ProductsWishListModel{
	private String productId;
	private String inventoryStatus;
}
