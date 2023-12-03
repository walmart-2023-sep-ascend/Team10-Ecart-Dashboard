package com.ascend.userdashboard.model;

import java.util.ArrayList;

public class Product{
 public int id;
 public String title;
 public int minQuantity;
 public String gender;
 public String iconUrl;
 public String shortDescription;
 public String longDescription;
 public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getMinQuantity() {
	return minQuantity;
}
public void setMinQuantity(int minQuantity) {
	this.minQuantity = minQuantity;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getIconUrl() {
	return iconUrl;
}
public void setIconUrl(String iconUrl) {
	this.iconUrl = iconUrl;
}
public String getShortDescription() {
	return shortDescription;
}
public void setShortDescription(String shortDescription) {
	this.shortDescription = shortDescription;
}
public String getLongDescription() {
	return longDescription;
}
public void setLongDescription(String longDescription) {
	this.longDescription = longDescription;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getProductCategory() {
	return productCategory;
}
public void setProductCategory(String productCategory) {
	this.productCategory = productCategory;
}
public String getInventryStatus() {
	return inventryStatus;
}
public void setInventryStatus(String inventryStatus) {
	this.inventryStatus = inventryStatus;
}
public int getAvailableQuantity() {
	return availableQuantity;
}
public void setAvailableQuantity(int availableQuantity) {
	this.availableQuantity = availableQuantity;
}
public String getPurchasable() {
	return purchasable;
}
public void setPurchasable(String purchasable) {
	this.purchasable = purchasable;
}
public ArrayList<String> getSearchTags() {
	return searchTags;
}
public void setSearchTags(ArrayList<String> searchTags) {
	this.searchTags = searchTags;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
public String getSpecification() {
	return specification;
}
public void setSpecification(String specification) {
	this.specification = specification;
}
public int getWarrantyDuration() {
	return warrantyDuration;
}
public void setWarrantyDuration(int warrantyDuration) {
	this.warrantyDuration = warrantyDuration;
}
public ArrayList<String> getImageUrls() {
	return imageUrls;
}
public void setImageUrls(ArrayList<String> imageUrls) {
	this.imageUrls = imageUrls;
}
public int getOrderLimit() {
	return orderLimit;
}
public void setOrderLimit(int orderLimit) {
	this.orderLimit = orderLimit;
}
public int getReturnDates() {
	return returnDates;
}
public void setReturnDates(int returnDates) {
	this.returnDates = returnDates;
}
public int getLength() {
	return length;
}
public void setLength(int length) {
	this.length = length;
}
public int getWidth() {
	return width;
}
public void setWidth(int width) {
	this.width = width;
}
public int getHeight() {
	return height;
}
public void setHeight(int height) {
	this.height = height;
}
public double getWeight() {
	return weight;
}
public void setWeight(double weight) {
	this.weight = weight;
}
public Ratings getRatings() {
	return ratings;
}
public void setRatings(Ratings ratings) {
	this.ratings = ratings;
}
public boolean isElegibileForPromotion() {
	return isElegibileForPromotion;
}
public void setElegibileForPromotion(boolean isElegibileForPromotion) {
	this.isElegibileForPromotion = isElegibileForPromotion;
}
public int getDiscount() {
	return discount;
}
public void setDiscount(int discount) {
	this.discount = discount;
}
public String getIsHazardous() {
	return isHazardous;
}
public void setIsHazardous(String isHazardous) {
	this.isHazardous = isHazardous;
}
public String getIsReturnable() {
	return isReturnable;
}
public void setIsReturnable(String isReturnable) {
	this.isReturnable = isReturnable;
}
public String productName;
 public String productCategory;
 public String inventryStatus;
 public int availableQuantity;
 public String purchasable;
 public ArrayList<String> searchTags;
 public String model;
 public String brand;
 public String specification;
 public int warrantyDuration;
 public ArrayList<String> imageUrls;
 public int orderLimit;
 public int returnDates;
 public int length;
 public int width;
 public int height;
 public double weight;
 public Ratings ratings;
 public boolean isElegibileForPromotion;
 public int discount;
 public String isHazardous;
 public String isReturnable;
}
