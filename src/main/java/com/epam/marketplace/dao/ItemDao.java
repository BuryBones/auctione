package com.epam.marketplace.dao;

import java.util.List;
import java.util.Optional;
import com.epam.marketplace.entities.Item;

public interface ItemDao extends CommonDao<Item> {

  default Class<Item> getType() {
    return Item.class;
  }

  Optional<Item> findByName(String name);

  List<Item> findByUserId(int userId);

  List<Item> findByUserIdWithDeals(int userId);
}
