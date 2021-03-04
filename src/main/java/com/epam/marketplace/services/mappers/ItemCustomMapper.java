package com.epam.marketplace.services.mappers;

import com.epam.marketplace.entities.Item;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.services.dto.ItemDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class ItemCustomMapper extends CustomMapper<Item, ItemDto> {

  @Override
  public void mapAtoB(Item src, ItemDto dest, MappingContext context) {
    dest.setId(src.getId());
    dest.setName(src.getName());
    dest.setDescript(src.getDescript());
    dest.setUserId(src.getUser().getId());
  }

  @Override
  public void mapBtoA(ItemDto src, Item dest, MappingContext context) {
    User user = new User();
    user.setId(src.getUserId());
    dest.setUser(user);

    dest.setName(src.getName());
    dest.setDescript(src.getDescript());
  }
}
