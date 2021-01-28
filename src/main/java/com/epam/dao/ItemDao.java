package com.epam.dao;

import java.util.Optional;
import com.epam.entities.Item;

public interface ItemDao extends CommonDao<Item> {

  default Class<Item> getType() {
    return Item.class;
  }

  Optional<Item> findByName(String name);

}
