package com.spring.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.model.OrderStatus;

public class OrderStatusMapper implements RowMapper<OrderStatus> {
	@Override
	public OrderStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderStatus status = new OrderStatus();
		status.setStatus_id(rs.getInt("status_id"));
		status.setName(rs.getString("name"));
		return status;
	}
}
