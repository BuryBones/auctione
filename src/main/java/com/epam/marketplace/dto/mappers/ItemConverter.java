package com.epam.marketplace.dto.mappers;

import com.epam.marketplace.entities.Item;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.dto.ItemDto;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("ItemConverter")
@Scope("prototype")
public class ItemConverter extends BidirectionalConverter<Item, ItemDto> {

  @Override
  public ItemDto convertTo(Item src, Type<ItemDto> type, MappingContext mappingContext) {
    ItemDto dest = new ItemDto();
    dest.setId(src.getId());
    dest.setName(src.getName());
    dest.setDescript(src.getDescript());
    dest.setUserId(src.getUser().getId());
    return dest;
  }

  @Override
  public Item convertFrom(ItemDto src, Type<Item> type, MappingContext mappingContext) {
    Item dest = new Item();
    User user = new User();
    user.setId(src.getUserId());
    dest.setUser(user);

    dest.setName(src.getName());
    if (src.getDescript() == null || src.getDescript().isEmpty()) {
      dest.setDescript("No Description");
    } else {
      dest.setDescript(src.getDescript());
    }
    return dest;
  }
}
