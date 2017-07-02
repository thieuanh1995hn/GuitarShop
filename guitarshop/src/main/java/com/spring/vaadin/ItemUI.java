package com.spring.vaadin;


import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.spring.dao.CategoryDao;
import com.spring.dao.ItemDao;

import com.spring.model.Item;
import com.spring.vaadin.model.VaadinItemShow;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import com.vaadin.ui.Button;

import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;



import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import com.vaadin.ui.VerticalLayout;

@Title("Items manager")
@Theme("valo")
@SpringUI(path = "/admin/items")
public class ItemUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ItemDao itemDao;
	private final CategoryDao categoryDao;
	private final ItemEditor editor;
	private final Grid<VaadinItemShow> grid;
	private final TextField filterTxtField;
	private final Button addNewBtn;
	@Value("${vaadin.servlet.url-mapping}")
	private String contextPath;
	private final Button homePageBtn;
	private final Button viewMyPageBtn;

	@Autowired
	public ItemUI(ItemDao itemDao, ItemEditor editor, CategoryDao categoryDao) {

		this.homePageBtn = new Button("Go to home page");
		this.viewMyPageBtn = new Button("Go to my page");
		this.itemDao = itemDao;
		this.categoryDao = categoryDao;
		this.editor = editor;
		this.grid = new Grid<>(VaadinItemShow.class);
		this.filterTxtField = new TextField();
		this.addNewBtn = new Button("Add new Items");
	}

	@Override
	protected void init(VaadinRequest request) {

		HorizontalLayout actions = new HorizontalLayout(filterTxtField, addNewBtn, homePageBtn, viewMyPageBtn);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
		setContent(mainLayout);

		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		grid.setHeight(300, Sizeable.Unit.PIXELS);
		grid.setColumns("name", "brandname", "color", "shape", "price", "number", "category");

		grid.getColumns().get(1).setCaption("Brand name");
		grid.setColumnReorderingAllowed(true);
		grid.setWidth(100, Unit.PERCENTAGE);

		filterTxtField.setPlaceholder("Filter by Name");
		filterTxtField.addValueChangeListener(e -> listItems(e.getValue()));

		grid.addSelectionListener(e -> {
			if (e.getFirstSelectedItem() == null) {
				editor.setVisible(false);
			} else {
				List<VaadinItemShow> its = new ArrayList<VaadinItemShow>();
				its.addAll(grid.getSelectionModel().getSelectedItems());
				if (!its.isEmpty()) {
					VaadinItemShow selectedItem = (VaadinItemShow) its.get(0);
					Item item = itemDao.getItemsById(selectedItem.getItem_id());
					editor.editItem(item);

				}
			}
		});

		addNewBtn.addClickListener(e -> {
			grid.deselectAll();
			Item newItem = new Item("", "", "", "", "", "", 0, 0, categoryDao.getAllCategories().get(0));
			editor.editItem(newItem);

		});

		editor.setChangeHandler(() -> {
			// editor.setVisible(false);
			grid.deselectAll();
			listItems(filterTxtField.getValue());
		});

		viewMyPageBtn.addClickListener(e -> getUI().getPage().setLocation(contextPath));
		homePageBtn.addClickListener(e -> getUI().getPage().setLocation("/home"));
		listItems(null);
	}

	private void listItems(String text) {
		if (StringUtils.isEmpty(text)) {
			int count = itemDao.countAllItems();
			List<Item> items = itemDao.getAllItems(0, count, 0);
			List<VaadinItemShow> itemslist = new ArrayList<VaadinItemShow>();
			for (Item item : items) {
				VaadinItemShow its = new VaadinItemShow();
				its.convertFromItem(item);
				itemslist.add(its);
			}
			grid.setItems(itemslist);

		} else {
			int count = itemDao.countItemsSearch(text);
			List<Item> items = itemDao.searchItem(text, 0, count, 0);
			List<VaadinItemShow> itemslist = new ArrayList<VaadinItemShow>();
			for (Item item : items) {
				VaadinItemShow its = new VaadinItemShow();
				its.convertFromItem(item);
				itemslist.add(its);
			}
			grid.setItems(itemslist);
		}
	}
}
