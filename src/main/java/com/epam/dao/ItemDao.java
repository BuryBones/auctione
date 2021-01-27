package com.epam.dao;

import java.util.Optional;
import com.epam.entities.Item;

public interface ItemDao extends CommonDao<Item> {

  Optional<Item> findByName(String name);

}
