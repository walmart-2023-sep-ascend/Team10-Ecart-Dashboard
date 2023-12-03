package com.ascend.userdashboard.model;

import java.util.ArrayList;

public class ModifiedProduct {
	public int id;
    public String title;
    public String shortDescription;
    public int discount;
    public String imageUrls;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	@Override
	public String toString() {
		return "ModifiedProduct [id=" + id + ", title=" + title + ", shortDescription=" + shortDescription
				+ ", discount=" + discount + ", imageUrls=" + imageUrls + "]";
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}
}
