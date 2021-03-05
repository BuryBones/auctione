package com.epam.marketplace.dto.mappers;

import com.epam.marketplace.entities.Item;
import com.epam.marketplace.dto.ItemDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component("itemMapper")
public class ItemMapper {

  private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
  private final ItemCustomMapper customMapper = new ItemCustomMapper();

  public ItemDto getDtoFromEntity(Item item) {
    mapperFactory.classMap(Item.class, ItemDto.class)
        .customize(customMapper)
        .register();
    MapperFacade mapper = mapperFactory.getMapperFacade();
    return mapper.map(item,ItemDto.class);
  }

  public Item getEntityFromDto(ItemDto itemDto) {
    mapperFactory.classMap(Item.class, ItemDto.class)
        .customize(customMapper)
        .register();
    MapperFacade mapper = mapperFactory.getMapperFacade();
    return mapper.map(itemDto, Item.class);
  }

}
