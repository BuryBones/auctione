package com.epam.marketplace.dao;

import com.epam.marketplace.entities.Item;
import java.util.List;
import java.util.Optional;

public interface ItemDao extends CommonDao<Item> {

  default Class<Item> getType() {
    return Item.class;
  }

  Optional<Item> findByName(String name);

  List<Item> findByUserId(int userId);

  List<Item> findByUserIdWithDeals(int userId);
}
