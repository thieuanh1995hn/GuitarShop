package com.spring.vaadin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.spring.dao.OrderDao;
import com.spring.model.ItemInCart;
import com.spring.model.Order;
import com.spring.model.User;
import com.spring.service.SecurityService;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;

import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Title("Orders history")
@Theme("valo")
@SpringUI(path = "/myorders")
public class OrderHistoryUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderDao orderDao;
	private final Grid<Order> grid;
	private final TextField filterTxtField;
	private final User user;
	private final GridLayout detailsLayout;
	private final Panel panelDetails;
	@Value("${vaadin.servlet.url-mapping}")
	private String contextPath;
	private final Button homePageBtn;
	private final Button viewMyPageBtn;
	private final Button cancelBtn;

	@Autowired
	public OrderHistoryUI(OrderDao orderDao, OrderEditor editor, SecurityService securityService) {
		this.orderDao = orderDao;
		this.cancelBtn = new Button("Cancel");
		this.grid = new Grid<>(Order.class);
		this.filterTxtField = new TextField();
		this.user = securityService.findLoggedInUser();
		this.detailsLayout = new GridLayout();

		this.panelDetails = new Panel("Order Details");
		this.panelDetails.setVisible(false);
		this.homePageBtn = new Button("Go to home page");
		this.viewMyPageBtn = new Button("Go to my page");
	}

	@Override
	protected void init(VaadinRequest request) {
		HorizontalLayout actions = new HorizontalLayout(filterTxtField,cancelBtn,homePageBtn,viewMyPageBtn);

		detailsLayout.setWidth(100, Unit.PERCENTAGE);
		panelDetails.setContent(detailsLayout);
		panelDetails.setWidth(70, Unit.PERCENTAGE);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, panelDetails);
		setContent(mainLayout);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		this.detailsLayout.setColumns(4);
		this.detailsLayout.setHeight(200, Sizeable.Unit.PIXELS);
		detailsLayout.setMargin(true);
		detailsLayout.setSpacing(true);
		grid.setHeight(300, Sizeable.Unit.PIXELS);
		grid.setColumns("order_id", "order_datetime", "receiver_name", "payment_method", "status");
		grid.getColumns().get(0).setCaption("Order ID");
		grid.getColumns().get(1).setCaption("Date Time");
		grid.getColumns().get(2).setCaption("Receiver Name");
		grid.getColumns().get(3).setCaption("Payment Method");
		grid.getColumns().get(4).setCaption("Status");
		grid.setColumnReorderingAllowed(true);
		grid.setWidth(100, Unit.PERCENTAGE);

		// filterTxtField.addTextChangeListener(new TextChangeListener() {
		//
		// @Override
		// public void textChange(TextChangeEvent event) {
		// listCustomers(event.getText());
		// }
		// });
		filterTxtField.setPlaceholder("Filter by ID");
		filterTxtField.addValueChangeListener(e -> listOrders(e.getValue()));
		cancelBtn.addClickListener(e -> panelDetails.setVisible(false));
		// grid.addSelectionListener(e -> {
		//
		// });
		grid.addSelectionListener(e -> {
			if (e.getFirstSelectedItem() != null) {
				List<Order> ods = new ArrayList<Order>();
				ods.addAll(grid.getSelectionModel().getSelectedItems());

				if (!ods.isEmpty()) {
					Order selectedOrder = (Order) ods.get(0);
					Order order = orderDao.getOrderByOrderId(selectedOrder.getOrder_id()).get(0);
					
					panelDetails.setVisible(true);
					detailsLayout.removeAllComponents();
					List<ItemInCart> details = orderDao.getOrderDetails(order.getOrder_id());
					Long sum = 0l;
					for (ItemInCart it : details) {
						Label label1 = new Label();
						label1.setValue(it.getItem().getName());
						Label label2 = new Label();
						label2.setValue(String.valueOf(it.getQuantity()));
						Label label3 = new Label();
						label3.setValue(String.valueOf(it.getItem().getPrice()) + "đ");
						Label label4 = new Label();
						label4.setValue(String.valueOf(it.getItem().getPrice() * it.getQuantity()) + "đ");
						detailsLayout.addComponent(label1);
						detailsLayout.addComponent(label2);
						detailsLayout.addComponent(label3);
						detailsLayout.addComponent(label4);
						sum = sum + it.getQuantity() * it.getItem().getPrice();
					}
					Label sumLabel = new Label();
					sumLabel.setHeight(350,Unit.PIXELS);
					sumLabel.setCaption("Total not include tax");
					sumLabel.setValue(String.valueOf(sum)+"đ");
					
					this.detailsLayout.addComponent(sumLabel);

				}
			}
		});
		viewMyPageBtn.addClickListener(e -> getUI().getPage().setLocation(contextPath));
		homePageBtn.addClickListener(e -> getUI().getPage().setLocation("/home"));
		listOrders(null);
	}

	private void listOrders(String text) {
		if (StringUtils.isEmpty(text)) {
			List<Order> orders = orderDao.getOrdersByUser(user);
			grid.setItems(orders);

		} else {
			try {
				int id = Integer.parseInt(text);
				List<Order> orders = orderDao.searchOrdersByUserAndOrderId(user, id);
				grid.setItems(orders);
			} catch (Exception e) {
				List<Order> orders = orderDao.searchOrdersByUserAndOrderId(user, 0);
				grid.setItems(orders);
			}

		}
	}
}
