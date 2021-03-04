package com.epam.marketplace.services.mappers;

import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.dao.impl.DealDaoImpl;
import com.epam.marketplace.dao.impl.UserDaoImpl;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.services.dto.BidDto;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class BidCustomMapper extends CustomMapper<Bid, BidDto> {

//   TODO: should not be here, probably??
  UserDao userDao = new UserDaoImpl();
  DealDao dealDao = new DealDaoImpl();

  @Override
  public void mapAtoB(Bid src, BidDto dest, MappingContext context) {
    dest.setId(src.getId());
    dest.setOffer(src.getOffer());
    dest.setDealId(src.getDeal().getId());
    dest.setUserId(src.getUser().getId());
    dest.setDateAndTime(Date.from(src.getDateAndTime().atZone(ZoneId.systemDefault()).toInstant()));
  }

  @Override
  public void mapBtoA(BidDto src, Bid dest, MappingContext context) {
    dest.setOffer(src.getOffer());
    // TODO: now?
    dest.setDateAndTime(LocalDateTime.now());
    Optional<User> optionalUser = userDao.findById(src.getUserId());
    if (optionalUser.isPresent()) {
      dest.setUser(optionalUser.get());
    } else {
      System.out.println("Failed to find USER in Bid mapper");
    }
    Optional<Deal> optionalDeal = dealDao.findById(src.getDealId());
    if (optionalDeal.isPresent()) {
      dest.setDeal(optionalDeal.get());
    } else {
      System.out.println("Failed to find DEAL in Bid mapper");
    }
  }
}
