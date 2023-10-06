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
@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

	@Id
	@MongoId(FieldType.OBJECT_ID)
	private String  _id;
	
	private Integer id;
	
	private String email;
	private String password;
	
	@Builder.Default
	private String validated ="NO";
	
	private List<String> interests;
	private String avtar;
	private String gender;
	private List<String> role;
	private String[] recentSearches;
	private String[] favoriteCategories;
	private String phone;
	private NameModel name;
	private AddressModel address;
	
}
