package com.spring.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dao.CategoryDao;
import com.spring.model.Category;
import com.spring.model.ItemInCart;
// Xu ly cac thao tac dieu huong cua trang
@Controller
@RequestMapping("/")
public class SiteViewController {
	@Autowired
	private CategoryDao categoryDao;
	List<Category> categories;	
	@PostConstruct
	private void init(){
		categories = categoryDao.getAllCategories();	
	}
	@RequestMapping(value="home", method = RequestMethod.GET)
	public String home() {

		return "redirect:/?p=1&size=6";
	}
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return this.categories;
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
	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public String contact(ModelMap model,HttpSession session) {
		
		return "contact";
	}
	@RequestMapping(value = "about", method = RequestMethod.GET)
	public String about(ModelMap model, HttpSession session) {
		
		
		return "about";
	}
}
