package com.epam.marketplace.services;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dao.impl.BidDaoImpl;
import com.epam.marketplace.dao.impl.DealDaoImpl;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.services.dto.AuctionRow;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service("defaultService")
public class DefaultService {

  public List<AuctionRow> getAuctions(String show) {
    DealDao dealDao = new DealDaoImpl();
    BidDao bidDao = new BidDaoImpl();
    // TODO: transfer directly to the mapper?
    List<Deal> deals = switch (show) {
      case "open" -> dealDao.findAllFullByStatus(true);
      case "closed" -> dealDao.findAllFullByStatus(false);
      case "all" -> dealDao.findAllFull();
      default -> Collections.emptyList();
    };
    ArrayList<AuctionRow> result = new ArrayList<>();
    for (Deal d: deals) {
      AuctionRow row = new AuctionRow();
      row.setSeller(d.getUser().getFirstName() + " " + d.getUser().getLastName());
      row.setItem(d.getItem().getName());
      row.setInfo(d.getItem().getDescript());
      row.setStartDate(d.getOpenTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
      row.setStartPrice(d.getInitPrice());
      Optional<Bid> optionalBid = bidDao.findLastBidByDealId(d.getId());
      optionalBid.ifPresent(bid -> row.setLastBid(bid.getOffer()));
      row.setStopDate(d.getCloseTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
      result.add(row);
    }
    return result;
  }

}
