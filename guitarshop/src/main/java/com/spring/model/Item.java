package com.spring.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Item {
	private Integer item_id;
	private String name;
	private String brandname;
	private String color;
	private String shape;
	private String images;
	private String info;
	private Integer price;
	private Integer number;
	private Category category;
	private List<Collection> collections;
	
	public Item(String name, String brandname, String color, String shape, String images, String info,
			Integer price, Integer number, Category category) {
		super();
		this.name = name;
		this.brandname = brandname;
		this.color = color;
		this.shape = shape;
		this.images = images;
		this.info = info;
		this.price = price;
		this.number = number;
		this.category = category;
		this.collections= new ArrayList<Collection>();
	
	}
	
	public Item() {
		super();
	}

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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Collection> getCollections() {
		return collections;
	}
	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}
	public Set<Collection> getSetCollections(){
		Set<Collection> setCollection = new HashSet<Collection>(getCollections());
		return  setCollection;
		
	}
	public void setSetCollections(Set<Collection> setCollection){
		this.collections = new ArrayList<Collection>(setCollection);
	}
}
