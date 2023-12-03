package com.ascend.userdashboard.repository;
import java.util.List;

import com.ascend.userdashboard.model.User;

public interface SearchRepository{
	public List<User> getUserSearches(int text);
}
