package com.spring.model;

public class Category {
	private Integer category_id;
	private String name;
	public Category(Integer category_id, String name) {
		super();
		this.category_id = category_id;
		this.name = name;
	}
	public Category() {
		this.category_id = new Integer(1);
		this.name=new String();
		
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
