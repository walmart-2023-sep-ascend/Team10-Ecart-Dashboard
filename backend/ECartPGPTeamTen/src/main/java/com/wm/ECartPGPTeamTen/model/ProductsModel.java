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

	public ProductsModel(Integer id, String productCategory, String brand) {
		this.id = id;
		this.brand = brand;
		this.productCategory = productCategory;
	}

	@Id
	@MongoId(FieldType.OBJECT_ID)
	private String _id;

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
	private String isHazardous;
	private String isReturnable;
	private RatingsModel ratings;

	@Override
	public String toString() {
		return "ProductsModel [_id=" + _id + ", id=" + id + ", title=" + title + ", minQuantity=" + minQuantity
				+ ", gender=" + gender + ", iconUrl=" + iconUrl + ", shortDescription=" + shortDescription
				+ ", longDescription=" + longDescription + ", productName=" + productName + ", productCategory="
				+ productCategory + ", inventryStatus=" + inventryStatus + ", availableQuantity=" + availableQuantity
				+ ", purchasable=" + purchasable + ", searchTags=" + searchTags + ", model=" + model + ", brand="
				+ brand + ", specification=" + specification + ", warrantyDuration=" + warrantyDuration + ", imageUrls="
				+ imageUrls + ", orderLimit=" + orderLimit + ", returnDates=" + returnDates + ", length=" + length
				+ ", width=" + width + ", height=" + height + ", weight=" + weight + ", isElegibileForPromotion="
				+ isElegibileForPromotion + ", discount=" + discount + ", isHazardous=" + isHazardous
				+ ", isReturnable=" + isReturnable + ", ratings=" + ratings + "]";
	}

}
