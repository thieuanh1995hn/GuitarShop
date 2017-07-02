package com.spring.service;

import java.util.List;
import java.util.Map;


import com.spring.model.Category;

public interface CategoryService {
	public Map<Integer, Category> getCategoriesMap();
	public List<Category> getAllCategories();
}
