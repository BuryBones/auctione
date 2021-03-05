package com.epam.marketplace.services;

import com.epam.marketplace.dao.ItemDao;
import com.epam.marketplace.entities.Item;
import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.dto.mappers.ItemMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemService {

  @Autowired
  private ItemDao itemDao;
  @Autowired
  private ItemMapper itemMapper;

  public List<ItemDto> getItemsByUserId(int userId) {
    List<Item> items = itemDao.findByUserId(userId);
    ArrayList<ItemDto> result = new ArrayList<>(items.size());
    for (Item i: items) {
      result.add(itemMapper.getDtoFromEntity(i));
    }
    return result;
  }

  public boolean createItem(ItemDto newBorn) {
    Item newItem = itemMapper.getEntityFromDto(newBorn);
    try {
      itemDao.save(newItem);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
