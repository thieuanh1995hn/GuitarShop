package com.spring.vaadin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.spring.model.Role;
import com.spring.service.SecurityService;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;

import com.vaadin.server.VaadinRequest;

import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Title("Items manager")
@Theme("valo")
@SpringUI()
public class MainUI extends UI {
	@Autowired
	SecurityService securityService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Button viewOrderFormBtn;
	private final Button viewItemFormBtn;
	private final Button viewMyOrdersBtn;
	private final Button homepageBtn;
	private final Label header;
	@Value("${vaadin.servlet.url-mapping}")
	private String contextPath;

	@Autowired
	public MainUI() {
		this.homepageBtn = new Button("Go to home page");
		this.viewOrderFormBtn = new Button("Go to orders manager page");
		this.viewItemFormBtn = new Button("Go to items manager page");
		this.viewMyOrdersBtn = new Button("View orders history");
		viewOrderFormBtn.setWidth(100, Unit.PERCENTAGE);
		viewMyOrdersBtn.setWidth(100, Unit.PERCENTAGE);
		viewItemFormBtn.setWidth(100, Unit.PERCENTAGE);
		homepageBtn.setWidth(100, Unit.PERCENTAGE);
		this.header = new Label("<h1><b>MY PAGE<b></h1>");
		header.setWidth(null);
		this.header.setContentMode(com.vaadin.shared.ui.ContentMode.HTML);

	}

	@Override
	protected void init(VaadinRequest request) {
		List<Role> roles = securityService.findLoggedInUser().getRoles();

		VerticalLayout mainLayout = null;

		for (Role role : roles) {
			if (role.getRole_id() == 2) {
				mainLayout = new VerticalLayout(header,homepageBtn, viewOrderFormBtn, viewItemFormBtn, viewMyOrdersBtn);
				mainLayout.setComponentAlignment(homepageBtn, Alignment.TOP_CENTER);
				mainLayout.setComponentAlignment(viewItemFormBtn, Alignment.TOP_CENTER);
				mainLayout.setComponentAlignment(viewMyOrdersBtn, Alignment.TOP_CENTER);
				mainLayout.setComponentAlignment(viewItemFormBtn, Alignment.TOP_CENTER);

			} else {
				mainLayout = new VerticalLayout(header,homepageBtn, viewMyOrdersBtn);
				mainLayout.setComponentAlignment(homepageBtn, Alignment.TOP_CENTER);
				mainLayout.setComponentAlignment(viewMyOrdersBtn, Alignment.TOP_CENTER);
			}
		}
		mainLayout.setWidth(null);
		mainLayout.setComponentAlignment(header, Alignment.TOP_CENTER);

		GridLayout editorlayout = new GridLayout();

		editorlayout.addComponent(mainLayout);
		editorlayout.setSizeFull();
		editorlayout.setComponentAlignment(mainLayout, Alignment.TOP_CENTER);

		editorlayout.setMargin(true);
		editorlayout.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		setContent(editorlayout);

		viewOrderFormBtn.addClickListener(e -> getUI().getPage().setLocation(contextPath + "/admin/orders"));
		viewItemFormBtn.addClickListener(e -> getUI().getPage().setLocation(contextPath + "/admin/items"));
		viewMyOrdersBtn.addClickListener(e -> getUI().getPage().setLocation(contextPath + "/myorders"));
		homepageBtn.addClickListener(e -> getUI().getPage().setLocation( "/home"));
	}

}
