package com.spring.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.spring.dao.impl.OrderDaoImpl;
import com.spring.dao.impl.UserDaoImpl;
import com.spring.model.Order;



public class OrderMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNumber) throws SQLException {
		UserDaoImpl userDao = new UserDaoImpl();
		OrderDaoImpl orderDao = new OrderDaoImpl();
		String url = "jdbc:mysql://localhost:3306/QLNhacCu?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "taytanhiu";
		userDao.setTemplate(new JdbcTemplate(new DriverManagerDataSource(url, username, password)));
		orderDao.setTemplate(new JdbcTemplate(new DriverManagerDataSource(url, username, password)));
		Order order = new Order();
		order.setOrder_id(rs.getInt("order_id"));
		order.setOrder_datetime(rs.getString("order_datetime"));
		order.setUser(userDao.getUserById( rs.getInt("user_id")));
		order.setReceiver_name(rs.getString("receiver_name"));
		order.setPostal_code(rs.getString("postal_code"));
		order.setDelivery_address(rs.getString("delivery_address"));
		order.setPayment_method(rs.getString("payment_method"));
		order.setReceiver_phone(rs.getString("receiver_phone"));
		order.setStatus(orderDao.getOrderStatusById(rs.getInt("status_id")));
		return order;
	}
	

		
}
