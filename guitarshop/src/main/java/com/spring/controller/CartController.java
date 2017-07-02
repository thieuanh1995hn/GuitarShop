package com.spring.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.model.ItemInCart;
import com.spring.model.Order;

import com.spring.service.SecurityService;
import com.spring.validator.OrderValidator;
import com.spring.dao.ItemDao;
import com.spring.dao.OrderDao;
import com.spring.model.Item;

//Xử lý liên quan đến giỏ hàng
@Controller
@RequestMapping("/")

public class CartController {
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderValidator orderValidator;
	@Autowired
	private SecurityService securityService;
	// Them vao gio hang

	@RequestMapping(value = "/addcart", method = RequestMethod.GET)
	public String addCart(@RequestParam("id") int id, @RequestParam("quantity") int quantity, ModelMap model,
			HttpSession session, HttpServletRequest request) {
		Item item = itemDao.getItemsById(id);
		ItemInCart itemInCart = new ItemInCart(item, quantity);
		if (session.getAttribute("cart") == null) {
			List<ItemInCart> itemsInCart = new ArrayList<ItemInCart>();
			itemsInCart.add(itemInCart);
			session.setAttribute("cart", itemsInCart);
		} else {
			@SuppressWarnings("unchecked")
			List<ItemInCart> itemsInCart = (List<ItemInCart>) session.getAttribute("cart");
			boolean inCart = false;
			for (ItemInCart it : itemsInCart) {
				if (it.getItem().getItem_id() == id) {
					inCart = true;
					int old_quantity = it.getQuantity();
					it.setQuantity(old_quantity + quantity);
				}
			}
			if (!inCart) {
				itemsInCart.add(itemInCart);
				System.out.println(itemsInCart.size());
			}

			session.removeAttribute("cart");
			session.setAttribute("cart", itemsInCart);
		}
		String referer = request.getHeader("Referer");

		return "redirect:" + referer + "#fsearch";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String viewCart(HttpSession session, ModelMap model) {

		if (session.getAttribute("cart") != null) {
			@SuppressWarnings("unchecked")
			List<ItemInCart> itemsInCart = (List<ItemInCart>) session.getAttribute("cart");
			double total = 0;
			for (ItemInCart it : itemsInCart) {
				total = total + it.getItem().getPrice() * it.getQuantity();
			}
			double tax = total * 10 / 100;
			double sum = total + tax;

			model.addAttribute("itemsincart", itemsInCart);
			model.addAttribute("total", total);
			model.addAttribute("tax", tax);
			model.addAttribute("sum", sum);

		}

		return "cart";
	}

	@RequestMapping(value = "/increase", method = RequestMethod.GET)
	public String increaseNumber(@RequestParam("item_id") int id, HttpSession session) {

		if (session.getAttribute("cart") != null) {
			@SuppressWarnings("unchecked")
			List<ItemInCart> itemsInCart = (List<ItemInCart>) session.getAttribute("cart");
			for (ItemInCart it : itemsInCart) {
				if (it.getItem().getItem_id() == id) {
					int oldquantity = it.getQuantity();
					it.setQuantity(oldquantity + 1);
				}
			}
			session.setAttribute("cart", itemsInCart);
		}
		return "redirect:/cart";
	}

	@RequestMapping(value = "/decrease", method = RequestMethod.GET)
	public String decreaseNumber(@RequestParam("item_id") int id, HttpSession session) {

		if (session.getAttribute("cart") != null) {
			@SuppressWarnings("unchecked")
			List<ItemInCart> itemsInCart = (List<ItemInCart>) session.getAttribute("cart");
			for (ItemInCart it : itemsInCart) {
				if (it.getItem().getItem_id() == id) {
					int oldquantity = it.getQuantity();
					it.setQuantity(oldquantity - 1);
				}
			}
			session.setAttribute("cart", itemsInCart);
		}
		return "redirect:/cart";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(Model model, HttpSession session) {
		List<ItemInCart> itemsInCart = new ArrayList<ItemInCart>();
		if (session.getAttribute("cart") != null) {
		 itemsInCart = (List<ItemInCart>) session.getAttribute("cart");
		}
		if (!itemsInCart.isEmpty()) {
			model.addAttribute("orderForm", new Order());
			model.addAttribute("currentUser", securityService.findLoggedInUser());
			return "checkout";
		} else {
			return "redirect:/cart";
		}
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String checkout(@ModelAttribute("orderForm") Order orderForm, BindingResult bindingResult, Model model,
			HttpSession session) {
		orderValidator.validate(orderForm, bindingResult);

		model.addAttribute("currentUser", securityService.findLoggedInUser());
		if (bindingResult.hasErrors()) {
			return "checkout";
		}
		// them not thuoc tinh user
		orderForm.setUser(securityService.findLoggedInUser());
		LocalDateTime now = LocalDateTime.now();
		String date = now.getYear() + "-" + String.format("%02d", now.getMonthValue()) + "-"
				+ String.format("%02d", now.getDayOfMonth()) + " " + String.format("%02d", now.getHour()) + ":"
				+ String.format("%02d", now.getMinute()) + ":" + String.format("%02d", now.getSecond());
		// them not thuoc tinh date
		orderForm.setOrder_datetime(date);
		String dateshow = String.format("%02d", now.getDayOfMonth()) + "-" + String.format("%02d", now.getMonthValue())
				+ "-" + now.getYear() + " " + String.format("%02d", now.getHour()) + ":"
				+ String.format("%02d", now.getMinute()) + ":" + String.format("%02d", now.getSecond());
		// add mot dang show ra mot neo
		model.addAttribute("dateshow", dateshow);
		if (session.getAttribute("cart") != null) {
			@SuppressWarnings("unchecked")
			List<ItemInCart> itemsInCart = (List<ItemInCart>) session.getAttribute("cart");
			model.addAttribute("itemsincart", itemsInCart);
			double total = 0;
			for (ItemInCart it : itemsInCart) {
				total = total + it.getItem().getPrice() * it.getQuantity();
			}
			double tax = total * 10 / 100;
			double sum = total + tax;
			model.addAttribute("tax", tax);
			model.addAttribute("sum", sum);
			session.setAttribute("orderForm", orderForm);
			return "checkoutconfirm";
		}
		return null;

	}

	@RequestMapping(value = "/checkout/addorder", method = RequestMethod.POST)
	public String addorder(Model model, HttpSession session) {
		try {
			@SuppressWarnings("unchecked")
			List<ItemInCart> itemsInCart = (List<ItemInCart>) session.getAttribute("cart");
			Boolean insert = true;
			String notEnoughItem = "";
			for (ItemInCart itc : itemsInCart) {
				if (itc.getQuantity() > itc.getItem().getNumber()) {
					insert = false;
					if (notEnoughItem.isEmpty()) {
						notEnoughItem = itc.getItem().getName();
					} else {
						notEnoughItem = notEnoughItem + ", " + itc.getItem().getName();
					}
				}
				if (insert) {
					Order orderForm = (Order) session.getAttribute("orderForm");
					int order_id = orderDao.insertOrder(orderForm, itemsInCart);
					if (order_id > 0) {
						model.addAttribute("order_id", order_id);
						model.addAttribute("email", orderForm.getUser().getEmail());
						session.removeAttribute("cart");
						session.removeAttribute("orderForm");
						return "ordersuccess";
					}
				} else {
					model.addAttribute("error", "Sản phẩm " + notEnoughItem + " không đủ số lượng  trong kho");
				}
			}
		} catch (SQLException ex) {
			model.addAttribute("error", "Không đủ số lượng sản phẩm trong kho");

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "failed";
	}

	@RequestMapping(value = "/deleteitemincart", method = RequestMethod.GET)
	public String deleteintemincart(HttpSession session, @RequestParam("id") int id, Model model) {
		@SuppressWarnings("unchecked")
		List<ItemInCart> itemsInCart = (List<ItemInCart>) session.getAttribute("cart");

		// Integer idDelete = null;
		// Integer count = itemsInCart.size();
		// for (int i = 0; i < count; i++) {
		// if (itemsInCart.get(i).getItem().getItem_id() == id) {
		// idDelete = i;
		//
		// break;
		// }
		// }
		// itemsInCart.remove(idDelete);

		Iterator<ItemInCart> itr = itemsInCart.iterator();
		while (itr.hasNext()) {
			if (itr.next().getItem().getItem_id() == id) {
				itr.remove();
			}
		}

		session.setAttribute("cart", itemsInCart);
		return "redirect:/cart";
	}
}
