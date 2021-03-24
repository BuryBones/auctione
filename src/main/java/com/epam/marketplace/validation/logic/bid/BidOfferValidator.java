package com.epam.marketplace.validation.logic.bid;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.exceptions.validity.ValidityException;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BidOfferValidator extends AbstractBidLogicValidator {

  private final Logger logger = Logger.getLogger("application");
  private final BidDao bidDao;

  @Autowired
  public BidOfferValidator(BidDao bidDao) {
    this.bidDao = bidDao;
  }

  @Override
  public void validate(BidDto dto) throws ValidityException {
    // check if offer is higher than the last offer
    Optional<Bid> optionalBid = bidDao.findLastBidByDealId(dto.getDealId());
    if (optionalBid.isPresent() && optionalBid.get().getOffer().compareTo(dto.getOffer()) >= 0) {
      logger.warning("Offer " + dto.getOffer() +
          " is lower than top bid " + optionalBid.get().getOffer());
      throw new ValidityException("Offer is not higher than the top offer");
    }
  }
}
