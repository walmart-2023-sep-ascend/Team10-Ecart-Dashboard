package com.wm.ECartPGPTeamTen.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * r0m09yu
 */

@Data
@Setter
@Getter
@Document(collection = "wishlist")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishListModel {

	@Id
	@MongoId(FieldType.OBJECT_ID)
	private String  _id;
	
	private Integer wishListId;
    private String userId;
    
    @DBRef
    private List<ProductsModel> products;
}

