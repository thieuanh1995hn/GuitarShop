package com.spring.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.spring.dao.CategoryDao;
import com.spring.model.Category;
import com.spring.model.mapper.CategoryMapper;
@Repository
public class CategoryDaoImpl implements CategoryDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	@Override
	public Map<Integer, Category> getCategoriesMap() {
		List<Category> categories = getAllCategories();
		Map<Integer, Category> categoriesMap = new HashMap<Integer, Category>();
		for (Category cate : categories) {
			categoriesMap.put(cate.getCategory_id(),cate);
		}
		return categoriesMap;	
	}


	@Override
	public List<Category> getAllCategories() {
	    if(this.jdbcTemplate==null)
	    {
	    System.out.print("JDBC TEMPLATE IS NULL");
	    }
		String query = "SELECT * FROM QLNhacCu.Categories";
		List<Category> categories = jdbcTemplate.query(query, new CategoryMapper());
		return categories;
	}
	

}
