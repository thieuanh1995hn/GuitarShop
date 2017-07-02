package com.spring.vaadin.model;

import com.spring.model.Item;

public class VaadinItemShow {
	private Integer item_id;
	private String name;
	private String brandname;
	private String color;
	private String shape;
	private String images;
	private String info;
	private Integer price;
	private Integer number;
	private String category;
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void convertFromItem(Item item){
		this.item_id = item.getItem_id();
		this.name = item.getName();
		this.brandname = item.getBrandname();
		this.color  = item.getColor();
		this.shape  = item.getShape();
		this.images  = item.getImages();
		this.info  = item.getInfo();
		this.price  = item.getPrice();
		this.number = item.getNumber();
		this.category = item.getCategory().getName();
	}
	
}
