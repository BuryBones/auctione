package com.epam.marketplace.validation.logic;

import com.epam.marketplace.OperationResult;
import com.epam.marketplace.dto.ItemDto;
import org.springframework.stereotype.Component;

@Component
public class ItemLogicValidator extends AbstractLogicValidator<ItemDto> {

  @Override
  public OperationResult validate(ItemDto dto) {
    // no logical validation is applicable for ItemDto yet.
    return OperationResult.success();
  }
}
