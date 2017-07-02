package com.spring.dao;

import java.util.List;

import com.spring.model.Collection;
import com.spring.model.Item;

public interface ItemDao  {
 //Lấy một lượng bản ghi theo tham số truyền vào để show lên page và lấy số lượng tổng thể của item
 public List<Item> getAllItems(Integer v,int limit, int offset);
 public Integer countAllItems();
 //--//
 public List<Item> getFourHotItems();
 //--//
 public List<Item> getItemsFromCategory(Integer category_id, Integer v , int limit , int offset);
 public Integer countItemsFromCategory(Integer category_id);
 //--//
 public List<Item> getItemsOnSale(Integer category_id);
 public List<Item> getItemsOnSale();
 //--//
 public List<Item> searchItem(String s, Integer v , int limit , int offset);
 public Integer countItemsSearch(String s);
 //--//
 public Item getItemsById(Integer id);
 public List<Collection> getAllCollections();
 //--//
 public int updateItem(Item item);
 public int insertItem(Item item);
 public int getLastIdInserted();
 public int deleteItem(Item item);
}
