package com.epam.marketplace.services.mappers;

import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.services.dto.BidDto;
import java.time.ZoneId;
import java.util.Date;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class BidCustomMapper extends CustomMapper<Bid, BidDto> {

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
    dest.setDateAndTime(src.getDateAndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

    User user = new User();
    user.setId(src.getUserId());
    dest.setUser(user);

    Deal deal = new Deal();
    deal.setId(src.getDealId());
    dest.setDeal(deal);
  }
}
