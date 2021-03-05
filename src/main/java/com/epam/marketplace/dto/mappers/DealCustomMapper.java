package com.epam.marketplace.dto.mappers;

import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.Item;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.dto.DealDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class DealCustomMapper extends CustomMapper<Deal, DealDto> {

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
    dest.setStatus(src.getStatus());

    User user = new User();
    user.setId(src.getSellerId());
    dest.setUser(user);

    Item item = new Item();
    item.setId(src.getItemId());
    dest.setItem(item);
  }
}
