package com.epam.marketplace.validation.logic.bid;

import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.Deal_;
import com.epam.marketplace.exceptions.validity.ValidityException;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BidOwnerValidator extends AbstractBidLogicValidator {

  private final Logger logger = Logger.getLogger("application");
  private final DealDao dealDao;

  @Autowired
  public BidOwnerValidator(DealDao dealDao) {
    this.dealDao = dealDao;
  }

  @Override
  public void validate(BidDto dto) throws ValidityException {
    // check if bidder id is not seller id
    // TODO: may that be easier?
    Optional<Deal> optionalDeal = dealDao.findByIdWithAttributes(dto.getDealId(), Deal_.user);
    if (optionalDeal.isPresent() && dto.getUserId().equals(optionalDeal.get().getUser().getId())) {
      logger.warning("Bidder ID = Seller ID");
      throw new ValidityException("Seller and bidder cannot be the same");
    }
  }
}
