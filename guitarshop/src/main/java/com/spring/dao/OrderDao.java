package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.model.ItemInCart;
import com.spring.model.Order;
import com.spring.model.OrderStatus;
import com.spring.model.User;

public interface OrderDao {
	public Integer countAllOrders();

	public List<Order> getAllOrders();
	public List<Order> getOrdersByUser(User user);
	public List<Order> searchOrdersByUserAndOrderId(User user,int id);
	public int insertOrder(Order order,List<ItemInCart> itemsInCart) throws SQLException;
	public List<Order> getOrderByOrderId(int id);
	public OrderStatus getOrderStatusById(int id);
	public List<OrderStatus> getAllOrderStatus();
	public int updateOrderStatus(Order order);
	public List<ItemInCart> getOrderDetails(int order_id);
	
}
