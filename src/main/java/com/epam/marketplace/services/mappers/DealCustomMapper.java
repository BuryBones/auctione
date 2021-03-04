package com.epam.marketplace.services.mappers;

import com.epam.marketplace.dao.ItemDao;
import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.dao.impl.ItemDaoImpl;
import com.epam.marketplace.dao.impl.UserDaoImpl;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.Item;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.services.dto.DealDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

// TODO: make an inner class of DealMapper?
public class DealCustomMapper extends CustomMapper<Deal, DealDto> {

  // TODO: should not be here, probably??
  ItemDao itemDao = new ItemDaoImpl();
  UserDao userDao = new UserDaoImpl();

  @Override
  public void mapAtoB(Deal src, DealDto dest, MappingContext context) {
    dest.setId(src.getId());
    dest.setSeller(src.getUser().getLastName() + " " + src.getUser().getFirstName());
    dest.setItem(src.getItem().getName());
    dest.setInfo(src.getItem().getDescript());
    dest.setStartDate(Date.from(src.getOpenTime().atZone(ZoneId.systemDefault()).toInstant()));
    Set<Bid> bidSet = src.getBids();
    if (bidSet.isEmpty()) {
      dest.setLastBid(new BigDecimal("0"));
    } else {
//      dest.setLastBid(bidSet.stream().findFirst().get().getOffer());
      dest.setLastBid(bidSet.stream().map(Bid::getOffer).max(Comparator.naturalOrder()).get());
    }
    dest.setStartPrice(src.getInitPrice());
    dest.setStopDate(Date.from(src.getCloseTime().atZone(ZoneId.systemDefault()).toInstant()));
    dest.setStatus(src.getCloseTime().isAfter(LocalDateTime.now()));
  }

  @Override
  public void mapBtoA(DealDto src, Deal dest, MappingContext context) {
    dest.setInitPrice(src.getStartPrice());
    dest.setOpenTime(src.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    dest.setCloseTime(src.getStopDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    dest.setStatus(true);
    Optional<User> optionalUser = userDao.findById(src.getSellerId());
    if (optionalUser.isPresent()) {
      dest.setUser(optionalUser.get());
    } else {
      System.out.println("Cannot find USER in mapper!");
    }
    Optional<Item> optionalItem = itemDao.findById(src.getItemId());
    if (optionalItem.isPresent()) {
      dest.setItem(optionalItem.get());
    } else {
      System.out.println("Cannot find ITEM in mapper!");
    }
  }
}
