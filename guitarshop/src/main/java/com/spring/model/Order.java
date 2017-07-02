package com.spring.model;

public class Order {
	private int order_id;
	private String order_datetime;
	private User user;
	private String receiver_name;
	private String postal_code;
	private String delivery_address;
	private String Payment_method;
	private String receiver_phone;
	private OrderStatus status;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getOrder_datetime() {
		return order_datetime;
	}

	public void setOrder_datetime(String order_datetime) {
		this.order_datetime = order_datetime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	public String getPayment_method() {
		return Payment_method;
	}

	public void setPayment_method(String payment_method) {
		Payment_method = payment_method;
	}

	public String getReceiver_phone() {
		return receiver_phone;
	}

	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}
