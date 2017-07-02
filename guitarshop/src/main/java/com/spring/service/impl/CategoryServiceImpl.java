package com.spring.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.CategoryDao;
import com.spring.model.Category;
import com.spring.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	@Override
	public Map<Integer, Category> getCategoriesMap() {
		
		return categoryDao.getCategoriesMap();
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}
	

}
