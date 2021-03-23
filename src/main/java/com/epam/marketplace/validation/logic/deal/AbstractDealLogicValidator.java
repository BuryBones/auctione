package com.epam.marketplace.validation.logic.deal;

import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.validation.logic.AbstractLogicValidator;
import com.epam.marketplace.validation.logic.ValidatorType;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDealLogicValidator extends AbstractLogicValidator<DealDto> {

  protected final ValidatorType type = ValidatorType.DEAL;

  @Override
  public ValidatorType getType() {
    return type;
  }
}
