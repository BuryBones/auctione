package main.java.com.epam.dao;

import java.util.Optional;
import main.java.com.epam.entities.Item;

public interface ItemDao extends CommonDao<Item> {

  Optional<Item> getItemByName(String name);

}
