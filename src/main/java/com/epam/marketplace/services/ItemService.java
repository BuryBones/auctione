package com.epam.marketplace.services;

import com.epam.marketplace.OperationResult;
import com.epam.marketplace.dao.ItemDao;
import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.Item;
import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.validation.ConstraintsValidator;
import com.epam.marketplace.validation.logic.AbstractLogicValidator;
import com.epam.marketplace.validation.logic.DealLogicValidator;
import com.epam.marketplace.validation.logic.ItemLogicValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemService {

  private final Logger logger = Logger.getLogger("application");
  private final ItemDao itemDao;
  private final CommonMapper mapper;
  private final ConstraintsValidator constraintsValidator;
  private final ItemLogicValidator itemLogicValidator;

  @Autowired
  public ItemService(ItemDao itemDao, CommonMapper mapper,
      ConstraintsValidator constraintsValidator,
      ItemLogicValidator itemLogicValidator) {
    this.itemDao = itemDao;
    this.mapper = mapper;
    this.constraintsValidator = constraintsValidator;
    this.itemLogicValidator = itemLogicValidator;
  }

  public List<ItemDto> getItemsByUserId(int userId) {
    List<Item> items = itemDao.findByUserIdWithDeals(userId);
    logger.info("For user #" + userId + " found " + items.size() + " items");
    ArrayList<ItemDto> result = new ArrayList<>(items.size());
    for (Item i: items) {
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

  public OperationResult createItem(ItemDto newBorn) {
    OperationResult validationResult = validate(newBorn);
    if (validationResult.getResult()) {
      Item newItem = mapper.getEntityFromDto(newBorn);
      return itemDao.save(newItem);
    } else {
      return validationResult;
    }
  }

  public OperationResult validate(ItemDto itemDto) {
    OperationResult constraintsValidationResult = constraintsValidator.validate(itemDto);
    if (constraintsValidationResult.getResult()) {
      return itemLogicValidator.validate(itemDto);
    } else {
      return constraintsValidationResult;
    }
  }
}
