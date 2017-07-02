package com.spring.service;

import java.util.List;


import com.spring.model.Item;


public interface ItemService {
	 public List<Item> getAllItem(Integer v, int limit , int offset);

}
