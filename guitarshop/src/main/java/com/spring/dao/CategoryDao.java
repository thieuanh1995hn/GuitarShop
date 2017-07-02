package com.spring.dao;

import java.util.List;
import java.util.Map;

import com.spring.model.Category;



public interface CategoryDao {
	public Map<Integer, Category> getCategoriesMap();
	public List<Category> getAllCategories();
}
