package com.epam.marketplace.services;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.dto.BidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bidService")
public class BidService {

  private final BidDao bidDao;
  private final CommonMapper mapper;

  @Autowired
  public BidService(BidDao bidDao, CommonMapper mapper) {
    this.bidDao = bidDao;
    this.mapper = mapper;
  }

  public boolean createBid(BidDto newBorn) {
    Bid newBid = mapper.getEntityFromDto(newBorn);
    try {
      bidDao.save(newBid);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

}
