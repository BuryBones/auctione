package com.epam.marketplace.services;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dao.impl.BidDaoImpl;
import com.epam.marketplace.dao.impl.DealDaoImpl;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.services.dto.AuctionRow;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service("defaultService")
public class DefaultService {

  public List<AuctionRow> getAuctions(String status, String sortBy, String sortMode) {
    DealDao dealDao = new DealDaoImpl();
    BidDao bidDao = new BidDaoImpl();
    // TODO: transfer directly to the mapper?
    List<Deal> deals = switch (status) {
      case "open" -> dealDao.findAllFullByStatus(true);
      case "closed" -> dealDao.findAllFullByStatus(false);
      case "all" -> dealDao.findAllFull();
      default -> Collections.emptyList();
    };
    ArrayList<AuctionRow> result = new ArrayList<>();
    for (Deal d: deals) {
      AuctionRow row = new AuctionRow();
      row.setId(d.getId());
      row.setSeller(d.getUser().getFirstName() + " " + d.getUser().getLastName());
      row.setItem(d.getItem().getName());
      row.setInfo(d.getItem().getDescript());
      row.setStartDate(d.getOpenTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
      row.setStartPrice(d.getInitPrice());
      Optional<Bid> optionalBid = bidDao.findLastBidByDealId(d.getId());
      if (optionalBid.isPresent()) {
        row.setLastBid(optionalBid.get().getOffer());
      } else {
        row.setLastBid(new BigDecimal("0"));
      }
      row.setStopDate(d.getCloseTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
      row.setStatus(d.getStatus());
      result.add(row);
    }
    return sort(result ,sortBy);
  }

  private List<AuctionRow> sort(ArrayList<AuctionRow> list, String sortBy) {
    Stream<AuctionRow> stream = list.stream();
    return switch (sortBy) {
      case "id" -> stream.sorted(Comparator.comparing(AuctionRow::getId,Comparator.naturalOrder())).collect(Collectors.toList());
      case "seller" -> stream.sorted(Comparator.comparing(AuctionRow::getSeller,Comparator.naturalOrder())).collect(Collectors.toList());
      case "item" -> stream.sorted(Comparator.comparing(AuctionRow::getItem,Comparator.naturalOrder())).collect(Collectors.toList());
      case "startDate" -> stream.sorted(Comparator.comparing(AuctionRow::getStartDate,Comparator.naturalOrder())).collect(Collectors.toList());
      case "stopDate" -> stream.sorted(Comparator.comparing(AuctionRow::getStopDate,Comparator.naturalOrder())).collect(Collectors.toList());
      case "startPrice" -> stream.sorted(Comparator.comparing(AuctionRow::getStartPrice,Comparator.naturalOrder())).collect(Collectors.toList());
      case "lastBid" -> stream.sorted(Comparator.comparing(row -> row.getLastBid(),Comparator.naturalOrder())).collect(Collectors.toList());
      default -> stream.sorted(Comparator.comparing(AuctionRow::getId,Comparator.naturalOrder())).collect(Collectors.toList());
    };
  }

}
