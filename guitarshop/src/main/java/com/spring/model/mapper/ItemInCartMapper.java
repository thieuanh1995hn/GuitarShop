package com.spring.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.spring.dao.impl.ItemDaoImpl;
import com.spring.model.ItemInCart;

public class ItemInCartMapper implements RowMapper<ItemInCart> {

	@Override
	public ItemInCart mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemDaoImpl itemDao = new ItemDaoImpl(); 
		String url = "jdbc:mysql://localhost:3306/QLNhacCu?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "taytanhiu";
		itemDao.setTemplate(new JdbcTemplate(new DriverManagerDataSource(url, username, password)));
		ItemInCart itemInCart = new ItemInCart();
		itemInCart.setItem(itemDao.getItemsById(rs.getInt("item_id")));
		itemInCart.setQuantity(rs.getInt("quantity"));
		return itemInCart;
	}
	
}
