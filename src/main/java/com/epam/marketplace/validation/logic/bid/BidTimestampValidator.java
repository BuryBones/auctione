package com.epam.marketplace.validation.logic.bid;

import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class BidTimestampValidator extends AbstractBidLogicValidator {

  private final Logger logger = Logger.getLogger("application");

  @Override
  public void validate(BidDto dto) throws ValidityException {
    // check if bid time is close to now
    if (!verifyDateMaxLag(dto.getDateAndTime(), maxLag)) {
      logger.warning("Bid timestamp is too old: " + dto.getDateAndTime());
      throw new ValidityException(
          "The time of the bid is too far in the past (more than " + maxLag + " milliseconds)");
    }
  }
}
