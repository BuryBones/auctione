package com.epam.marketplace.services;

import com.epam.marketplace.dao.ItemDao;
import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  private final Logger logger = Logger.getLogger("application");
  private final ItemDao itemDao;
  private final CommonMapper mapper;
  private final UserService userService;

  @Autowired
  public ItemService(ItemDao itemDao, CommonMapper mapper, UserService userService) {
    this.itemDao = itemDao;
    this.mapper = mapper;
    this.userService = userService;
  }

  public List<ItemDto> getItemsByUserId(int userId) {
    List<Item> items = itemDao.findByUserIdWithDeals(userId);
    logger.info("For user #" + userId + " found " + items.size() + " items");
    return getDtoList(items);
  }

  public void createItem(ItemDto newBorn) {
    setNewbornFields(newBorn);
    itemDao.save(mapper.getEntityFromDto(newBorn));
  }

  private List<ItemDto> getDtoList(List<Item> entities) {
    ArrayList<ItemDto> result = new ArrayList<>(entities.size());
    for (Item item : entities) {
      ItemDto itemDto = mapper.getDtoFromEntity(item);
      item.getDeals().forEach(deal -> itemDto.addDealId(deal.getId()));
      itemDto.setOnSale(checkIfOnSale(item));
      result.add(itemDto);
      logger.info("Item #" + itemDto.getId() + " onSale: " + itemDto.getOnSale());
    }
    return result;
  }

  private boolean checkIfOnSale(Item entity) {
    return entity.getDeals().stream()
            .anyMatch(Deal::getStatus);
  }

  private void setNewbornFields(ItemDto newBorn) {
    newBorn.setUserId(userService.getCurrentUserId());
  }
}
