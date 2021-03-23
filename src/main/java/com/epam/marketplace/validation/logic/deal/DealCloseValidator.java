package com.epam.marketplace.validation.logic.deal;

import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class DealCloseValidator extends AbstractDealLogicValidator {

  private final Logger logger = Logger.getLogger("application");

  @Override
  public void validate(DealDto dto) throws ValidityException {
    // check if close date is more than minimum delay after now
    if (!verifyDateMinDelay(dto.getStopDate(), minDelay)) {
      logger.warning("Deal stop date is too soon: " + dto.getStopDate());
      throw new ValidityException(
          "The close time of the deal is too soon (less than " + maxLag + " milliseconds)");
    }
  }
}
