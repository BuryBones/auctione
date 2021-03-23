package com.epam.marketplace.validation.logic.deal;

import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class DealOpenValidator extends AbstractDealLogicValidator {

  private final Logger logger = Logger.getLogger("application");

  @Override
  public void validate(DealDto dto) throws ValidityException {
    // check if open time is close to now
    if (!verifyDateMaxLag(dto.getStartDate(), maxLag)) {
      logger.warning("Deal start date is too old: " + dto.getStartDate());
      throw new ValidityException(
          "The open time of the deal is too far in the past (more than " + maxLag
              + " milliseconds)");
    }
  }
}
