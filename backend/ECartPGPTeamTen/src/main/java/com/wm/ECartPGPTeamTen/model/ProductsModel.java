package com.wm.ECartPGPTeamTen.model;

import java.util.List;

import org.springframework.data.annotation.Id;
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
@Document(collection = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsModel {

	@Id
	@MongoId(FieldType.OBJECT_ID)
	private String  _id;
	
	private Integer id;			
	private String title;			
	private Integer minQuantity;	
	private String gender;		
	private String iconUrl;		
	private String shortDescription;
	private String longDescription;
	private String productName;	
	private String productCategory;
	private String inventryStatus;
	private Integer availableQuantity;
	private String purchasable;
	private List<String> searchTags;
	
	private String model;				
	private String brand;				
	private String specification;		
	private Integer warrantyDuration;		
	private List<String> imageUrls;			
	private Integer orderLimit;	
	private Integer returnDates;	
	private Integer length;		
	private Integer width;		
	private Integer height;		
	private Integer weight;	
	
	private Boolean isElegibileForPromotion;
	private Integer discount;
	private String  isHazardous;
	private String  isReturnable;
	private RatingsModel ratings;


}
