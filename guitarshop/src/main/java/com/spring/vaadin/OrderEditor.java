package com.spring.vaadin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.spring.dao.CategoryDao;
import com.spring.dao.OrderDao;
import com.spring.model.Order;
import com.spring.model.OrderStatus;
import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SpringComponent
@UIScope
public class OrderEditor extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Order order;

	ComboBox<OrderStatus> cbstatus = new ComboBox<OrderStatus>("Update Status");
	GridLayout editorlayout = new GridLayout();
	Button save = new Button("Save");
	Button cancel = new Button("Cancel");
	Notification noti = new Notification("This is notification");

	@SuppressWarnings("static-access")
	@Autowired
	public OrderEditor(OrderDao orderDao, CategoryDao categoryDao) {

		editorlayout.setSizeFull();
		editorlayout.setRows(2);
		editorlayout.setColumns(4);
		List<OrderStatus> statusdata = orderDao.getAllOrderStatus();
		cbstatus.setItems(statusdata);
		cbstatus.setItemCaptionGenerator(p -> p.getName());
		editorlayout.addComponent(cbstatus);
		HorizontalLayout hl = new HorizontalLayout();
		hl.addComponents(save, cancel);
		this.addComponents(hl, editorlayout);

		save.addClickListener(clickEvent -> {
			int aft = orderDao.updateOrderStatus(order);
			if (aft == 1) {
				noti.show("Update Status Success", Notification.Type.TRAY_NOTIFICATION);
				this.setVisible(false);
			} else {
				noti.show("Update Status Failed", Notification.Type.ERROR_MESSAGE);
			}
		});
		cancel.addClickListener(clickEvent -> this.setVisible(false));

	}

	public void init() {
		Binder<Order> binder = new Binder<Order>(Order.class);
		binder.setBean(order);
		binder.forField(cbstatus).bind(Order::getStatus, Order::setStatus);
	}

	public final void editOrder(Order o) {
		this.order = o;
		setVisible(true);
		init();
		save.focus();

	}

	public interface ChangeHandler {
		void onChange();
	}

	public void setChangeHandler(ChangeHandler h) {
		save.addClickListener(e -> h.onChange());
	}

}
