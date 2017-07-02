package com.spring.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



import com.spring.dao.impl.CategoryDaoImpl;
import com.spring.model.Item;

public class ItemMapper implements RowMapper<Item> {

	
	@Override
	public Item mapRow(ResultSet rs, int rowNumber) throws SQLException {
		CategoryDaoImpl categoryDao = new CategoryDaoImpl();
		String url = "jdbc:mysql://localhost:3306/QLNhacCu?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "taytanhiu";
		categoryDao.setJdbcTemplate(new JdbcTemplate(new DriverManagerDataSource(url, username, password)));
		Item item = new Item();
		item.setItem_id(rs.getInt("item_id"));
		item.setName(rs.getString("name"));
		item.setBrandname(rs.getString("brandname"));
		item.setColor(rs.getString("color"));
		item.setShape(rs.getString("shape"));
		item.setImages(rs.getString("images"));
		item.setInfo(rs.getString("info"));
		item.setPrice(rs.getInt("price"));
		item.setNumber(rs.getInt("number"));
		item.setCategory(categoryDao.getCategoriesMap().get((Integer) rs.getInt("category_id")));
		return item;
	}

}
