package com.epam.marketplace.validation.logic.bid;

import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.validation.logic.AbstractLogicValidator;
import com.epam.marketplace.validation.logic.ValidatorType;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractBidLogicValidator extends AbstractLogicValidator<BidDto> {

  protected final ValidatorType type = ValidatorType.BID;

  @Override
  public ValidatorType getType() {
    return type;
  }
}
