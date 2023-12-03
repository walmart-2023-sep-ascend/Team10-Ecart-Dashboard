package com.ascend.userdashboard.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;


//public class User {
//
//        @Id
//        private int id;
//
//        private String name;
//        private String address;
//		public int getId() {
//			return id;
//		}
//		public void setId(int id) {
//			this.id = id;
//		}
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public String getAddress() {
//			return address;
//		}
//		public void setAddress(String address) {
//			this.address = address;
//		}
//		@Override
//		public String toString() {
//			return "User [id=" + id + ", name=" + name + ", address=" + address + "]";
//		}
//		public User(int id, String name, String address) {
//			super();
//			this.id = id;
//			this.name = name;
//			this.address = address;
//		}
//        
//        
//}











//import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
//import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root[] root = om.readValue(myJsonString, Root[].class); */
 class Address{
 public Geolocation geolocation;
 public String city;
 public String street;
 public String number;
 public String zipcode;
}

 class Geolocation{
 public Lat lat;
}

class Lat{
 public double lat;
 @JsonProperty("long") 
 public double mylong;
}

class Name{
 public String firstName;
 public String lastName;
}

@Document("user")
public class User{
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", avtar=" + avtar + ", validated="
				+ validated + ", interests=" + interests + ", name=" + name + ", gender=" + gender + ", role=" + role
				+ ", securityQuestion=" + securityQuestion + ", phone=" + phone + ", address=" + address
				+ ", favoriteCategories=" + favoriteCategories + ", recentSearches=" + recentSearches + "]";
	}
	@Id
 public org.bson.types.ObjectId id;
 public String email;
 public String password;
 public String avtar;
 public org.bson.types.ObjectId getId() {
	return id;
}
public void setId(org.bson.types.ObjectId id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAvtar() {
	return avtar;
}
public void setAvtar(String avtar) {
	this.avtar = avtar;
}
public String getValidated() {
	return validated;
}
public void setValidated(String validated) {
	this.validated = validated;
}
public ArrayList<String> getInterests() {
	return interests;
}
public void setInterests(ArrayList<String> interests) {
	this.interests = interests;
}
public Name getName() {
	return name;
}
public void setName(Name name) {
	this.name = name;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public ArrayList<String> getRole() {
	return role;
}
public void setRole(ArrayList<String> role) {
	this.role = role;
}
public ArrayList<SecurityQuestion> getSecurityQuestion() {
	return securityQuestion;
}
public void setSecurityQuestion(ArrayList<SecurityQuestion> securityQuestion) {
	this.securityQuestion = securityQuestion;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
public ArrayList<String> getFavoriteCategories() {
	return favoriteCategories;
}
public void setFavoriteCategories(ArrayList<String> favoriteCategories) {
	this.favoriteCategories = favoriteCategories;
}
public ArrayList<String> getRecentSearches() {
	return recentSearches;
}
public void setRecentSearches(ArrayList<String> recentSearches) {
	this.recentSearches = recentSearches;
}
public String validated;
 public ArrayList<String> interests;
 public Name name;
 public String gender;
 public ArrayList<String> role;
 public ArrayList<SecurityQuestion> securityQuestion;
 public String phone;
 public Address address;
 public ArrayList<String> favoriteCategories;
 public ArrayList<String> recentSearches;
}

class SecurityQuestion{
 public String question;
 public String answer;
}

