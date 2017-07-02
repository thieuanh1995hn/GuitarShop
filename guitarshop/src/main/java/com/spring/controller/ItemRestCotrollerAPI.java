package com.spring.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dao.CategoryDao;
import com.spring.dao.ItemDao;

import com.spring.model.Category;
import com.spring.model.Item;

@RestController
@RequestMapping("/api")
public class ItemRestCotrollerAPI {
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private CategoryDao categoryDao;

	@RequestMapping(value = "items", method = RequestMethod.GET)
	public ResponseEntity<ItemsList> getAllItems(@RequestParam("v") Integer v, @RequestParam("p") Integer p,
			@RequestParam("size") Integer size, @RequestParam("linksrange") Integer linksRange) {
		Integer count = itemDao.countAllItems();
		Integer pages;
		if (count % size == 0) {
			pages = count / size;
		} else {
			pages = count / size + 1;
		}
		;
		if (p < 1) {
			p = 1;
		} else if (p > pages && pages > 0) {
			p = pages;
		}
		Integer offset = (p - 1) * size;
		List<Item> items = itemDao.getAllItems(v, size, offset);
		int firstpage = ((p - 1) / linksRange) * linksRange + 1;
		int lastpage = firstpage + linksRange - 1;
		if (items == null || items.isEmpty()) {
			return new ResponseEntity<ItemsList>(HttpStatus.NO_CONTENT);
		}
		ItemsList itemsList = new ItemsList(items, pages, firstpage, lastpage);
		return new ResponseEntity<ItemsList>(itemsList, HttpStatus.OK);
	}

	@RequestMapping(value = "searchresult", method = RequestMethod.GET)
	public ResponseEntity<ItemsList> searchItems(@RequestParam("v") Integer v, @RequestParam("search") String s,
			@RequestParam("p") Integer p, @RequestParam("size") Integer size,
			@RequestParam("linksrange") Integer linksRange) {
		Integer count = itemDao.countItemsSearch(s);
		Integer pages;
		if (count % size == 0) {
			pages = count / size;
		} else {
			pages = count / size + 1;
		}
		;
		if (p < 1) {
			p = 1;
		} else if (p > pages && pages > 0) {
			p = pages;
		}
		Integer offset = (p - 1) * size;
		List<Item> items = itemDao.searchItem(s, v, size, offset);
		int firstpage = ((p - 1) / linksRange) * linksRange + 1;
		int lastpage = firstpage + linksRange - 1;

		if (items == null || items.isEmpty()) {
			return new ResponseEntity<ItemsList>(HttpStatus.NO_CONTENT);
		}
		ItemsList itemsList = new ItemsList(items, pages, firstpage, lastpage);
		return new ResponseEntity<ItemsList>(itemsList, HttpStatus.OK);
	}

	@RequestMapping(value = "4hotitems", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> get4HotItems() {

		List<Item> items = itemDao.getFourHotItems();

		if (items == null || items.isEmpty()) {
			return new ResponseEntity<List<Item>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	@RequestMapping(value = "categories", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategories() {

		List<Category> categories = categoryDao.getAllCategories();

		if (categories == null || categories.isEmpty()) {
			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "itemsfromcategory", method = RequestMethod.GET)
	public ResponseEntity<ItemsList> getItemsFromCategory(@RequestParam("v") Integer v, @RequestParam("c") Integer c,
			@RequestParam("p") Integer p, @RequestParam("size") Integer size,
			@RequestParam("linksrange") Integer linksRange) {
		Integer count = itemDao.countItemsFromCategory(c);
		Integer pages;
		if (count % size == 0) {
			pages = count / size;
		} else {
			pages = count / size + 1;
		}
		;
		if (p < 1) {
			p = 1;
		} else if (p > pages && pages > 0) {
			p = pages;
		}
		Integer offset = (p - 1) * size;
		List<Item> items = itemDao.getItemsFromCategory(c, v, size, offset);
		int firstpage = ((p - 1) / linksRange) * linksRange + 1;
		int lastpage = firstpage + linksRange - 1;
		if (items == null || items.isEmpty()) {
			return new ResponseEntity<ItemsList>(HttpStatus.NO_CONTENT);
		}
		ItemsList itemsList = new ItemsList(items, pages, firstpage, lastpage);
		return new ResponseEntity<ItemsList>(itemsList, HttpStatus.OK);
	}

	@RequestMapping(value = "item", method = RequestMethod.GET)
	public ResponseEntity<Item> getItemById(@RequestParam("id") Integer id) {
		Item item = itemDao.getItemsById(id);
	
		File folder = new File("src/main/resources/static/images");
		File[] listOfFiles = folder.listFiles();
		//List<String> filteredList = new ArrayList<String>();

		for (File file : listOfFiles) {
		    // Be aware that folder.listFiles() give list with directories and files
		    if (file.isFile()) {
		        if(file.getName().toLowerCase().startsWith(item.getImages()+"-")) { 
		        	System.out.println(file.getName());
		        }
		    }
		}
		if (item == null) {
			return new ResponseEntity<Item>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}
	@RequestMapping(value = "images", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getImages(@RequestParam("img") Integer images) {
		File folder = new File("src/main/resources/static/images");
		File[] listOfFiles = folder.listFiles();
		List<String> filteredList = new ArrayList<String>();

		for (File file : listOfFiles) {
		    // Be aware that folder.listFiles() give list with directories and files
		    if (file.isFile()) {
		        if(file.getName().toLowerCase().startsWith(images+"-")) { 
		        	filteredList.add(file.getName());
		        }
		    }
		}
		if (filteredList == null || filteredList.isEmpty()) {
			return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<String>>(filteredList, HttpStatus.OK);
	}
	private class ItemsList {
		@SuppressWarnings("unused")
		public List<Item> items;
		@SuppressWarnings("unused")
		public Integer pages;
		@SuppressWarnings("unused")
		public Integer firstpage;
		@SuppressWarnings("unused")
		public Integer lastpage;

		public ItemsList(List<Item> items, Integer pages, Integer firstpage, Integer lastpage) {
			super();
			this.items = items;
			this.pages = pages;
			this.firstpage = firstpage;
			this.lastpage = lastpage;
		}
	}

}
