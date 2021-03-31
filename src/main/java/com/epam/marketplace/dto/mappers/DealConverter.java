package com.epam.marketplace.dto.mappers;

import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.Item;
import com.epam.marketplace.entities.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class DealConverter extends BidirectionalConverter<Deal, DealDto> {

  @Override
  public DealDto convertTo(Deal src, Type<DealDto> type, MappingContext mappingContext) {
    DealDto dest = new DealDto();
    dest.setId(src.getId());
    dest.setSeller(src.getUser().getLastName() + " " + src.getUser().getFirstName());
    dest.setSellerId(src.getUser().getId());
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
    return dest;
  }

  @Override
  public Deal convertFrom(DealDto src, Type<Deal> type, MappingContext mappingContext) {
    Deal dest = new Deal();
    dest.setInitPrice(src.getStartPrice());
    dest.setOpenTime(
        src.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    dest.setCloseTime(
        src.getStopDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    dest.setStatus(src.getStatus());

    User user = new User();
    user.setId(src.getSellerId());
    dest.setUser(user);

    Item item = new Item();
    item.setId(src.getItemId());
    dest.setItem(item);
    return dest;
  }
}
