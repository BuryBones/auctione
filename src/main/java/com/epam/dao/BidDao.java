package com.epam.dao;

import java.util.Date;
import java.util.List;
import com.epam.entities.Bid;

public interface BidDao extends CommonDao<Bid> {

  List<Bid> getBidsFromDate(Date from);

}
