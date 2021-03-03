package com.epam.marketplace.services.mappers;

import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.services.dto.DealDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

// TODO: make an inner class of DealMapper?
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
      dest.setLastBid(bidSet.stream().findFirst().get().getOffer());
    }
    dest.setStartPrice(src.getInitPrice());
    dest.setStopDate(Date.from(src.getCloseTime().atZone(ZoneId.systemDefault()).toInstant()));
    dest.setStatus(src.getCloseTime().isAfter(LocalDateTime.now()));
  }

  @Override
  public void mapBtoA(DealDto dealDto, Deal deal, MappingContext context) {
    super.mapBtoA(dealDto, deal, context);
  }
}
