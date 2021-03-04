package com.epam.marketplace.services.mappers;

import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.dao.impl.UserDaoImpl;
import com.epam.marketplace.entities.Item;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.services.dto.ItemDto;
import java.util.Optional;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class ItemCustomMapper extends CustomMapper<Item, ItemDto> {

  // TODO: remove from here somehow
  UserDao userDao = new UserDaoImpl();

  @Override
  public void mapAtoB(Item src, ItemDto dest, MappingContext context) {
    dest.setId(src.getId());
    dest.setName(src.getName());
    dest.setDescript(src.getDescript());
//    dest.setUserId(src.getUser().getId());
  }

  @Override
  public void mapBtoA(ItemDto src, Item dest, MappingContext context) {
    Optional<User> optionalUser = userDao.findById(src.getUserId());
    if (optionalUser.isPresent()) {
      dest.setUser(optionalUser.get());
    } else {
      System.out.println("Failed to find user in item mapper!");
    }
    dest.setName(src.getName());
    dest.setDescript(src.getDescript());
  }
}
