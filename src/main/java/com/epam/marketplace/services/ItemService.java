package com.epam.marketplace.services;

import com.epam.marketplace.dao.ItemDao;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Item;
import com.epam.marketplace.dto.ItemDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemService {

  private final ItemDao itemDao;
  private final CommonMapper mapper;

  @Autowired
  public ItemService(ItemDao itemDao, CommonMapper mapper) {
    this.itemDao = itemDao;
    this.mapper = mapper;
  }

  public List<ItemDto> getItemsByUserId(int userId) {
    List<Item> items = itemDao.findByUserId(userId);
    ArrayList<ItemDto> result = new ArrayList<>(items.size());
    for (Item i: items) {
      result.add(mapper.getDtoFromEntity(i));
    }
    return result;
  }

  public boolean createItem(ItemDto newBorn) {
    Item newItem = mapper.getEntityFromDto(newBorn);
    try {
      itemDao.save(newItem);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
