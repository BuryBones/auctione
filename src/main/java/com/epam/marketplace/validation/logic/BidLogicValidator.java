package com.epam.marketplace.validation.logic;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.Deal_;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BidLogicValidator extends AbstractLogicValidator<BidDto> {

  private final BidDao bidDao;
  private final DealDao dealDao;

  @Autowired
  public BidLogicValidator(BidDao bidDao, DealDao dealDao) {
    this.bidDao = bidDao;
    this.dealDao = dealDao;
  }

  @Override
  public String validate(BidDto dto) {
    // check if bid time is close to now
    if (!verifyDateMaxLag(dto.getDateAndTime(), maxLag)) {
      return "The time of the bid is too far in the past (more than " + maxLag + " milliseconds)";
    }
    // check if offer is higher than the last offer
    Optional<Bid> optionalBid = bidDao.findLastBidByDealId(dto.getDealId());
    if (optionalBid.isPresent() && optionalBid.get().getOffer().compareTo(dto.getOffer()) >= 0) {
      return "Offer is not higher than the top offer";
    }
    // check if bidder id is not seller id
    // TODO: may that be easier?
    Optional<Deal> optionalDeal = dealDao.findByIdWithAttributes(dto.getDealId(), Deal_.user);
    if (optionalDeal.isPresent() && dto.getUserId().equals(optionalDeal.get().getUser().getId())) {
      return "Seller and bidder cannot be the same";
    }
    return "ok";
  }
}
