package com.wm.userdashboard.vo;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data

public class UserDataResponseVO {
    public UserDataResponseVO(List<String> userInterests, List<String> favoriteCategories, List<String> recentSearches) {
		super();
		this.userInterests = userInterests;
		this.favoriteCategories = favoriteCategories;
		this.recentSearches = recentSearches;
	}
	private List<String> userInterests;
    private List<String> favoriteCategories;
    private List<String> recentSearches;

    // Constructors, getters, and setters
}
