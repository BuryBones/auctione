package com.epam.marketplace.services;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.dto.mappers.BidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bidService")
public class BidService {

  @Autowired
  private BidDao bidDao;
  @Autowired
  private BidMapper bidMapper;

  public boolean createBid(BidDto newBorn) {
    Bid newBid = bidMapper.getEntityFromDto(newBorn);
    try {
      bidDao.save(newBid);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

}
