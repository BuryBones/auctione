package main.java.com.epam.dao;

import main.java.com.epam.entities.Item;

public interface ItemDao extends CommonDao<Item> {

  Item getItemByName(String name);

}
