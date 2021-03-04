package com.epam.marketplace.services;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dao.impl.BidDaoImpl;
import com.epam.marketplace.dao.impl.DealDaoImpl;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.services.dto.BidDto;
import com.epam.marketplace.services.mappers.BidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bidService")
public class BidService {

//  @Autowired
  BidDao bidDao = new BidDaoImpl();
  DealDao dealDao = new DealDaoImpl();
  BidMapper bidMapper = new BidMapper();

  public boolean createBid(BidDto newBorn) {
    Bid newBid = bidMapper.getEntityFromDto(newBorn);
    try {
      bidDao.save(newBid);
//      dealDao.refresh(newBid.getDeal());
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

}
