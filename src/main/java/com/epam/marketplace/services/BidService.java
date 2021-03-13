package com.epam.marketplace.services;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.dto.mappers.BidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bidService")
public class BidService {

  private final BidDao bidDao;
  private final BidMapper bidMapper;

  @Autowired
  public BidService(BidDao bidDao, BidMapper bidMapper) {
    this.bidDao = bidDao;
    this.bidMapper = bidMapper;
  }

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
