package com.spring.controller;

import java.io.File;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.dao.CategoryDao;
import com.spring.dao.ItemDao;
import com.spring.dao.OrderDao;
import com.spring.dao.UserDao;
import com.spring.model.Category;
import com.spring.model.Item;
import com.spring.model.ItemInCart;

//Xử lý dữ liệu và điều hướng trang liên quan chính đến items
@Controller
@RequestMapping("/")
public class ItemController {
	@Autowired
	private ItemDao itemDao;
	private int pageLinksRange = 10;
	@Autowired
	private UserDao userDao;
	@Autowired 
	private OrderDao orderDao;
	@Autowired
	private CategoryDao categoryDao;
	List<Category> categories;

	@PostConstruct
	private void init() {
		categories = categoryDao.getAllCategories();
	}
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return this.categories;
	}
	@ModelAttribute("pageLinksRange")
	public int getPageLinkRange() {
		return this.pageLinksRange;
	}
	@ModelAttribute("numberincart")
	public int getNumberInCart(HttpSession session){
		if (session.getAttribute("cart") != null) {
			@SuppressWarnings("unchecked")
			List<ItemInCart> itemsInCart = (List<ItemInCart>) session.getAttribute("cart");
			if (itemsInCart.isEmpty()) {
				return 0;
			} else {
				int number = 0;
				for (ItemInCart itemInCart : itemsInCart) {
					number = number + itemInCart.getQuantity();
				}
				return number;
			}
		} else {
			return 0;
		}	
	}
	@RequestMapping(method = RequestMethod.GET)
	public String index(@RequestParam("p") Integer p, @RequestParam("size") Integer size, ModelMap model,
			HttpSession session) {
		List<Item> hotItems = itemDao.getFourHotItems();
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
		List<Item> items = itemDao.getAllItems(0, size, offset);
		int firstpage = ((p - 1) / pageLinksRange) * pageLinksRange + 1;
		int lastpage = firstpage + pageLinksRange - 1;
		int numberofitems = itemDao.countAllItems();
		int numberofusers = userDao.countAllUsers();
		int numberoforders = orderDao.countAllOrders();
		LocalDate now = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String startdate="2015/12/01";
		LocalDate start = LocalDate.parse(startdate, format);		
		long daysspent = now.toEpochDay() - start.toEpochDay();
		model.addAttribute("fpage", firstpage);
		model.addAttribute("lpage", lastpage);
		model.addAttribute("items", items);
		model.addAttribute("hotitems", hotItems);
		model.addAttribute("viewid", 0);
		model.addAttribute("pages", pages);
		model.addAttribute("pcursor", p);
		model.addAttribute("size", size);
		model.addAttribute("numberofitems", numberofitems);
		model.addAttribute("numberofusers", numberofusers);
		model.addAttribute("numberoforders", numberoforders);
		model.addAttribute("daysspent", daysspent);
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@RequestParam("v") Integer v, @RequestParam("p") Integer p, @RequestParam("size") Integer size,
			ModelMap model, HttpSession session) {
		List<Item> hotItems = itemDao.getFourHotItems();
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
		int firstpage = ((p - 1) / pageLinksRange) * pageLinksRange + 1;
		int lastpage = firstpage + pageLinksRange - 1;
		
		model.addAttribute("fpage", firstpage);
		model.addAttribute("lpage", lastpage);
		model.addAttribute("pages", pages);
		model.addAttribute("items", items);
		model.addAttribute("hotitems", hotItems);
		model.addAttribute("viewid", v);
		model.addAttribute("pcursor", p);
		model.addAttribute("size", size);
		return "index";
	}

	@RequestMapping(value = "items", method = RequestMethod.GET)
	public String items(@RequestParam("v") Integer v, @RequestParam("p") Integer p, @RequestParam("size") Integer size,
			ModelMap model,HttpSession session) {
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
		int firstpage = ((p - 1) / pageLinksRange) * pageLinksRange + 1;
		int lastpage = firstpage + pageLinksRange - 1;
		model.addAttribute("fpage", firstpage);
		model.addAttribute("lpage", lastpage);
		model.addAttribute("pages", pages);
		model.addAttribute("items", items);
		model.addAttribute("viewid", v);
		model.addAttribute("pcursor", p);
		model.addAttribute("size", size);
		return "items";
	}

	@RequestMapping(value = "items/{id}", method = RequestMethod.GET)
	public String itemspool(@PathVariable Integer id, @RequestParam("v") Integer v, @RequestParam("p") Integer p,
			@RequestParam("size") Integer size, ModelMap model,HttpSession session) {

		Integer count = itemDao.countItemsFromCategory(id);

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
		List<Item> items = itemDao.getItemsFromCategory(id, v, size, offset);
		int firstpage = ((p - 1) / pageLinksRange) * pageLinksRange + 1;
		int lastpage = firstpage + pageLinksRange - 1;
		
		model.addAttribute("fpage", firstpage);
		model.addAttribute("lpage", lastpage);
		model.addAttribute("items", items);
		model.addAttribute("viewid", v);
		model.addAttribute("pages", pages);
		model.addAttribute("pcursor", p);
		model.addAttribute("size", size);
		return "items";
	}

	@RequestMapping(value = "/searchresult", method = RequestMethod.GET)
	public String search(@RequestParam("v") Integer v, @RequestParam("search") String s, @RequestParam("p") Integer p,
			@RequestParam("size") Integer size, ModelMap model,HttpSession session) {
		Integer count = itemDao.countItemsSearch(s);
		List<Item> hotItems = itemDao.getFourHotItems();
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
		int firstpage = ((p - 1) / pageLinksRange) * pageLinksRange + 1;
		int lastpage = firstpage + pageLinksRange - 1;
		
		model.addAttribute("fpage", firstpage);
		model.addAttribute("lpage", lastpage);
		model.addAttribute("items", items);
		model.addAttribute("hotitems", hotItems);
		model.addAttribute("viewid", v);
		model.addAttribute("search", s);
		model.addAttribute("pages", pages);
		model.addAttribute("pcursor", p);
		model.addAttribute("size", size);
		return "searchresult";
	}

	@RequestMapping(value = "itemdetails", method = RequestMethod.GET)
	public String itemDetails(@RequestParam("id") Integer id, ModelMap model,HttpSession session) {
		Item item = itemDao.getItemsById(id);
		File folder = new File("src/main/resources/static/images");
		File[] listOfFiles = folder.listFiles();
		List<String> filteredList = new ArrayList<String>();
		for (File file : listOfFiles) {
			// Be aware that folder.listFiles() give list with directories and
			// files
			if (file.isFile()) {
				if (file.getName().toLowerCase().startsWith(item.getImages() + "-")) {
					filteredList.add(file.getName());
				}
			}
		}		
		model.addAttribute("item", item);
		model.addAttribute("images", filteredList);
		return "itemdetails";
	}
}
