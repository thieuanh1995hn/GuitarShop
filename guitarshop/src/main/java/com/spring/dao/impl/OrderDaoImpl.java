package com.spring.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.dao.OrderDao;
import com.spring.model.ItemInCart;
import com.spring.model.Order;
import com.spring.model.OrderStatus;
import com.spring.model.User;
import com.spring.model.mapper.ItemInCartMapper;
import com.spring.model.mapper.OrderMapper;
import com.spring.model.mapper.OrderStatusMapper;

@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return this.template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Integer countAllOrders() {
		String query = "SELECT COUNT(*) FROM QLNhacCu.Orders";
		Integer count = template.queryForObject(query, Integer.class);
		return count;
	}

	@Override
	public List<Order> getAllOrders() {
		String query = "SELECT * FROM QLNhacCu.Orders";
		List<Order> orders = template.query(query, new OrderMapper());
		return orders;
	}

	@Override
	public int insertOrder(Order order, List<ItemInCart> itemsInCart) throws SQLException {
		String query = "INSERT INTO QLNhacCu.Orders(order_datetime, user_id,receiver_name,postal_code,delivery_address,payment_method,receiver_phone) VALUES(?,?,?,?,?,?,?)";
		int affectedrows = template.update(query, order.getOrder_datetime(), order.getUser().getUser_id(),
				order.getReceiver_name(), order.getPostal_code(), order.getDelivery_address(),
				order.getPayment_method(), order.getReceiver_phone());
		if (affectedrows > 0) {
			String query_get_oid = "SELECT MAX(order_id) from QLNhacCu.Orders WHERE user_id ="
					+ order.getUser().getUser_id();
			Integer order_id = template.queryForObject(query_get_oid, Integer.class);
			String query2 = "INSERT INTO QLNhacCu.OrderDetails(order_id,item_id,quantity) VALUES (?,?,?)";
			for (ItemInCart it : itemsInCart) {
				template.update(query2, order_id, it.getItem().getItem_id(), it.getQuantity());
			}
			return order_id;
		}
		// affectedrows = 1 order + n orderdetails
		return 0;
	}

	@Override
	public List<Order> getOrdersByUser(User user) {
		String query = "SELECT * FROM QLNhacCu.Orders WHERE user_id=" + user.getUser_id();
		List<Order> orders = template.query(query, new OrderMapper());
		return orders;
	}

	@Override
	public List<Order> searchOrdersByUserAndOrderId(User user, int id) {
		String query = "SELECT * FROM QLNhacCu.Orders WHERE user_id=" + user.getUser_id() + " AND order_id=" + id;
		List<Order> orders = template.query(query, new OrderMapper());
		return orders;
	}

	@Override
	public List<Order> getOrderByOrderId(int id) {
		String query = "SELECT * FROM QLNhacCu.Orders WHERE  order_id=" + id;
		List<Order> orders = template.query(query, new OrderMapper());
		return orders;
	}

	@Override
	public OrderStatus getOrderStatusById(int id) {
		try {
			String query = "SELECT * FROM QLNhacCu.OrderStatus WHERE status_id=" + id;
			OrderStatus status = template.queryForObject(query, new OrderStatusMapper());
			return status;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<OrderStatus> getAllOrderStatus() {
		String query = "SELECT * FROM QLNhacCu.OrderStatus ";

		List<OrderStatus> statuslist = template.query(query, new OrderStatusMapper());
		return statuslist;
	}

	@Override
	public int updateOrderStatus(Order order) {
		if (order.getStatus() != null) {
			if (order.getStatus().getName().equals("Cancelled")) {
				String query2 = "UPDATE QLNhacCu.Orders SET status_id=" + order.getStatus().getStatus_id()
						+ ",restocked=1 WHERE order_id=" + order.getOrder_id();
				int affected = template.update(query2);
				return affected;
			} else {
				String query = "UPDATE  QLNhacCu.Orders SET status_id=" + order.getStatus().getStatus_id()
						+ " WHERE order_id=" + order.getOrder_id();
				int affected = template.update(query);
				return affected;
			}

		} else {
			String query = "UPDATE  QLNhacCu.Orders SET status_id= null WHERE order_id=" + order.getOrder_id();
			int affected = template.update(query);
			return affected;
		}
	}
	@Override
	public List<ItemInCart> getOrderDetails(int order_id) {
		String query = "SELECT item_id,quantity FROM QLNhacCu.OrderDetails WHERE order_id=" + order_id;
		List<ItemInCart> itemsInCart = template.query(query, new ItemInCartMapper());
		return itemsInCart;

	}

}
