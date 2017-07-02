package com.spring.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.dao.ItemDao;
import com.spring.model.Collection;
import com.spring.model.Item;
import com.spring.model.mapper.CollectionMapper;
import com.spring.model.mapper.ItemMapper;

@Repository
public class ItemDaoImpl implements ItemDao {
	@Autowired
	private JdbcTemplate template;
	 
	
	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public List<Item> getAllItems(Integer v, int limit, int offset) {
		String query = "SELECT * FROM QLNhacCu.Items WHERE visible=1 LIMIT " + limit + " OFFSET " + offset;
		if (v == 1) {
			query = "SELECT * FROM QLNhacCu.Items WHERE visible=1 ORDER BY price ASC LIMIT " + limit + " OFFSET "
					+ offset;
		}
		if (v == 2) {
			query = "SELECT * FROM QLNhacCu.Items WHERE visible=1 ORDER BY price DESC  LIMIT " + limit + " OFFSET "
					+ offset;
		}
		List<Item> items = (List<Item>) template.query(query, new ItemMapper());
		for (Item item : items) {
			String query2 = "SELECT b.collection_id , b.name  FROM QLNhacCu.CollectionDetails AS a INNER JOIN QLNhacCu.Collections AS b ON a.collection_id = b.collection_id  WHERE a.item_id ="
					+ item.getItem_id();
			item.setCollections((List<Collection>) template.query(query2, new CollectionMapper()));
		}
		return items;
	}

	@Override
	public Integer countAllItems() {
		String query = "SELECT count(*) as a FROM QLNhacCu.Items WHERE visible=1";
		Integer size = template.queryForObject(query, Integer.class);
		return size;
	}

	@Override
	public List<Item> getItemsOnSale(Integer category_id) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM QLNhacCu.Items inner join QLNhacCu.CollectionDetails on Items.item_id = CollectionDetails.item_id"
				+ " WHERE visible=1 AND CollectionDetails.collection_id = 4 and Items.category_id=" + category_id;
		List<Item> itemsonsale = (List<Item>) template.query(query, new ItemMapper());
		return itemsonsale;
	}

	@Override
	public List<Item> getItemsOnSale() {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM QLNhacCu.Items inner join QLNhacCu.CollectionDetails on Items.item_id = CollectionDetails.item_id"
				+ " WHERE visible=1 AND CollectionDetails.collection_id = 4";
		List<Item> itemsonsale = (List<Item>) template.query(query, new ItemMapper());
		return itemsonsale;
	}

	@Override
	public List<Item> getFourHotItems() {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM QLNhacCu.Items inner join QLNhacCu.CollectionDetails "
				+ "ON Items.item_id = CollectionDetails.item_id WHERE visible=1 AND collection_id = 5 ORDER BY Items.item_id DESC "
				+ "LIMIT 4";
		List<Item> items = (List<Item>) template.query(query, new ItemMapper());
		return items;
	}

	@Override
	public List<Item> getItemsFromCategory(Integer category_id, Integer v, int limit, int offset) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM QLNhacCu.Items  WHERE visible=1 AND category_id= " + category_id + " LIMIT "
				+ limit + " OFFSET " + offset;
		if (v == 1) {
			query = "SELECT * FROM QLNhacCu.Items WHERE visible=1 AND category_id= " + category_id
					+ " ORDER BY price ASC  LIMIT " + limit + " OFFSET " + offset;
		}
		if (v == 2) {
			query = "SELECT * FROM QLNhacCu.Items WHERE visible=1 AND category_id= " + category_id
					+ " ORDER BY price DESC  LIMIT " + limit + " OFFSET " + offset;
		}
		List<Item> items = (List<Item>) template.query(query, new ItemMapper());
		for (Item item : items) {
			String query2 = "SELECT b.collection_id , b.name  FROM QLNhacCu.CollectionDetails AS a INNER JOIN QLNhacCu.Collections AS b ON a.collection_id = b.collection_id  WHERE a.item_id ="
					+ item.getItem_id();
			item.setCollections((List<Collection>) template.query(query2, new CollectionMapper()));
		}
		return items;
	}

	@Override
	public Item getItemsById(Integer id) {
		String query = "SELECT * FROM QLNhacCu.Items WHERE  item_id=" + id;
		Item item = template.queryForObject(query, new ItemMapper());
		String query2 = "SELECT b.collection_id , b.name  FROM QLNhacCu.CollectionDetails AS a INNER JOIN QLNhacCu.Collections AS b ON a.collection_id = b.collection_id  WHERE a.item_id ="
				+ item.getItem_id();
		item.setCollections((List<Collection>) template.query(query2, new CollectionMapper()));
		return item;

	}

	@Override
	public Integer countItemsFromCategory(Integer category_id) {
		String query = "SELECT count(*) as a FROM QLNhacCu.Items WHERE visible=1 AND category_id =" + category_id;
		Integer size = template.queryForObject(query, Integer.class);
		return size;
	}

	@Override
	public List<Item> searchItem(String s, Integer v, int limit, int offset) {
		String query = "SELECT * from QLNhacCu.Items WHERE visible=1 AND Items.name like '%" + s + "%' LIMIT " + limit
				+ " OFFSET " + offset;
		;
		if (v == 1) {
			query = "SELECT * from QLNhacCu.Items WHERE visible=1 AND Items.name like '%" + s
					+ "%' ORDER BY price ASC LIMIT " + limit + " OFFSET " + offset;

		}
		if (v == 2) {
			query = "SELECT * from QLNhacCu.Items WHERE visible=1 AND Items.name like '%" + s
					+ "%' ORDER BY price DESC LIMIT " + limit + " OFFSET " + offset;
		}
		List<Item> items = (List<Item>) template.query(query, new ItemMapper());
		for (Item item : items) {
			String query2 = "SELECT b.collection_id , b.name  FROM QLNhacCu.CollectionDetails AS a INNER JOIN QLNhacCu.Collections AS b ON a.collection_id = b.collection_id  WHERE a.item_id ="
					+ item.getItem_id();
			item.setCollections((List<Collection>) template.query(query2, new CollectionMapper()));
		}
		return items;
	}

	@Override
	public Integer countItemsSearch(String s) {
		String query = "SELECT count(*) as a FROM QLNhacCu.Items WHERE visible=1 AND Items.name like '%" + s + "%'";
		Integer size = template.queryForObject(query, Integer.class);
		return size;
	}

	@Override
	public List<Collection> getAllCollections() {
		String query = "SELECT * FROM QLNhacCu.Collections ORDER BY  collection_id ASC";
		List<Collection> collections = template.query(query, new CollectionMapper());
		return collections;
	}
	// Them sua Item

	@Override
	public int updateItem(Item item) {
		String query = "UPDATE QLNhacCu.Items SET name=?,brandname=?,color=?,shape=?,info=?,price=?,number=?,category_id=?  WHERE item_id=?";
		int affectedrows = template.update(query, item.getName(), item.getBrandname(), item.getColor(), item.getShape(),
				item.getInfo(), item.getPrice(), item.getNumber(), item.getCategory().getCategory_id(),
				item.getItem_id());
		if (affectedrows == 1) {
			Item preItem = getItemsById(item.getItem_id());
			for (Collection co : item.getCollections()) {
				Boolean insert = true;
				for (Collection co2 : preItem.getCollections()) {
					if (co.getCollection_id() == co2.getCollection_id()) {
						insert = false;
						break;
					}
				}
				if (insert) {
					String query2 = "INSERT INTO QLNhacCu.CollectionDetails (item_id,collection_id) VALUES (?,?)";
					template.update(query2, item.getItem_id(), co.getCollection_id());
				}
			}
			for (Collection co : preItem.getCollections()) {
				Boolean delete = true;
				for (Collection co2 : item.getCollections()) {
					if (co.getCollection_id() == co2.getCollection_id()) {
						delete = false;
						break;
					}
				}
				if (delete) {
					String query2 = "DELETE FROM QLNhacCu.CollectionDetails WHERE item_id=? AND collection_id=?";
					template.update(query2, item.getItem_id(), co.getCollection_id());
				}
			}
		}
		return affectedrows;
	}

	@Override
	public int insertItem(Item item) {
		String query = "INSERT INTO QLNhacCu.Items (name,brandname,color,shape,info,price,number,category_id,visible) VALUES (?,?,?,?,?,?,?,?,?)";
		int affectedrows = template.update(query, item.getName(), item.getBrandname(), item.getColor(), item.getShape(),
				item.getInfo(), item.getPrice(), item.getNumber(), item.getCategory().getCategory_id(), 1);

		if (affectedrows == 1) {
			String query2 = "UPDATE  QLNhacCu.Items SET images= LAST_INSERT_ID() WHERE item_id= LAST_INSERT_ID()";
			template.update(query2);
			for (Collection co : item.getCollections()) {
				String query3 = "INSERT INTO QLNhacCu.CollectionDetails (item_id,collection_id) VALUES (LAST_INSERT_ID(),?)";
				template.update(query3, co.getCollection_id());
			}
		}
		return affectedrows;
	}

	@Override
	public int getLastIdInserted() {
		String query = "SELECT LAST_INSERT_ID()";
		Integer id = template.queryForObject(query, Integer.class);
		return id;
	}

	@Override
	public int deleteItem(Item item) {
		String query1 = "SELECT count(*) FROM orderdetails o INNER JOIN items i WHERE o.item_id=" + item.getItem_id();
		Integer count = template.queryForObject(query1, Integer.class);
		if (count > 0) {
			String query2 = "UPDATE QLNhacCu.Items SET visible =0 WHERE item_id=" + item.getItem_id();
			int affectedrows = template.update(query2);
			return affectedrows;
		} else {
			String query2 = "DELETE FROM QLNhacCu.Items WHERE item_id=" + item.getItem_id();
			int affectedrows = template.update(query2);
			return affectedrows;
		}
	}
}
