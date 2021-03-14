package com.epam.marketplace.dto.mappers;

import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.dto.BidDto;
import java.time.ZoneId;
import java.util.Date;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bidConverter")
@Scope("prototype")
public class BidConverter extends BidirectionalConverter<Bid,BidDto> {

  @Override
  public BidDto convertTo(Bid src, Type<BidDto> type, MappingContext mappingContext) {
    BidDto dest = new BidDto();
    dest.setId(src.getId());
    dest.setOffer(src.getOffer());
    dest.setDealId(src.getDeal().getId());
    dest.setUserId(src.getUser().getId());
    dest.setDateAndTime(Date.from(src.getDateAndTime().atZone(ZoneId.systemDefault()).toInstant()));
    return dest;
  }

  @Override
  public Bid convertFrom(BidDto src, Type<Bid> type, MappingContext mappingContext) {
    Bid dest = new Bid();
    dest.setOffer(src.getOffer());
    dest.setDateAndTime(src.getDateAndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

    User user = new User();
    user.setId(src.getUserId());
    dest.setUser(user);

    Deal deal = new Deal();
    deal.setId(src.getDealId());
    dest.setDeal(deal);
    return dest;
  }
}
