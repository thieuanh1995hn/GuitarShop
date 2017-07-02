package com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.ItemDao;
import com.spring.model.Item;
import com.spring.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemDao itemDao;
	@Override
	public List<Item> getAllItem(Integer v, int limit , int offset) {
		// TODO Auto-generated method stub
		return itemDao.getAllItems(v, limit, offset);
	}

}
