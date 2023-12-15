package com.wm.userdashboard.vo;

import java.util.List;

import lombok.Data;

/**
 * Value Object (VO) representing the response containing user data.
 * 
 * This class holds information about user interests, favorite categories, and recent searches.
 * 
 * s0k05sw
 */
@Data
public class UserDataResponseVO {

    /**
     * Constructs a new instance of UserDataResponseVO.
     * 
     * @param userInterests      List of user interests.
     * @param favoriteCategories List of user's favorite categories.
     * @param recentSearches     List of user's recent searches.
     */
    public UserDataResponseVO(List<String> userInterests, List<String> favoriteCategories,
            List<String> recentSearches) {
        super();
        this.userInterests = userInterests;
        this.favoriteCategories = favoriteCategories;
        this.recentSearches = recentSearches;
    }    
    private List<String> userInterests;    
    private List<String> favoriteCategories;    
    private List<String> recentSearches;
}
