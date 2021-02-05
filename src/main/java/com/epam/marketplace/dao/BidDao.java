package com.epam.marketplace.dao;

import java.time.LocalDateTime;
import java.util.List;
import com.epam.marketplace.entities.Bid;

public interface BidDao extends CommonDao<Bid> {

  default Class<Bid> getType() {
    return Bid.class;
  }

  List<Bid> findFromDate(LocalDateTime from);

}
