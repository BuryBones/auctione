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
    ArrayList<ItemDto> result = new ArrayList<>(items.size());
    for (Item i : items) {
      ItemDto itemDto = mapper.getDtoFromEntity(i);
      i.getDeals().forEach(deal -> itemDto.addDealId(deal.getId()));

      // set boolean 'isOnSale' on 'true' for item if there is an active deal for this item
      itemDto.setOnSale(
          i.getDeals().stream()
              .findAny().filter(Deal::getStatus).isPresent());
      logger.info("Item #" + itemDto.getId() + " onSale: " + itemDto.getOnSale());
      result.add(itemDto);
    }
    return result;
  }

  public void createItem(ItemDto newBorn) {
    newBorn.setUserId(userService.getCurrentUserId());
    Item newItem = mapper.getEntityFromDto(newBorn);
    itemDao.save(newItem);
  }
}
