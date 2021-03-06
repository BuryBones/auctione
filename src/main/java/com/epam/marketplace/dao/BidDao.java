package com.epam.marketplace.dao;

import com.epam.marketplace.entities.Bid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BidDao extends CommonDao<Bid> {

  default Class<Bid> getType() {
    return Bid.class;
  }

  List<Bid> findFromDate(LocalDateTime from);

  Optional<Bid> findLastBidByDealId(int dealId);

}
