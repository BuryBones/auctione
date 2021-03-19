package com.epam.marketplace.internal;

import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dao.ItemDao;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.Item;
import com.epam.marketplace.entities.User;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TimerTask;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dealCloserTask")
public class DealCloserTask extends TimerTask {

  private final DealDao dealDao;
  private final ItemDao itemDao;

  @Autowired
  public DealCloserTask(DealDao dealDao, ItemDao itemDao) {
    this.dealDao = dealDao;
    this.itemDao = itemDao;
  }

  // TODO: write javadoc, change methods name in dealDao, change 'status' column in DB
  @Override
  public void run() {
    List<Deal> openDeals = dealDao.findAllFullByStatus(true);   // find deals with unresolved status

    LocalDateTime now = LocalDateTime.now();

    List<Deal> overdueDeals = openDeals.stream()
      .filter(d -> d.getCloseTime().isBefore(now))  // check if deal is overdue
      .collect(Collectors.toList());

    for (Deal d: overdueDeals) {
      Item soldItem = d.getItem();
      Optional<Bid> optionalBid = d.getBids().stream().max(Comparator.comparing(Bid::getOffer));
      if (optionalBid.isPresent()) {    // change the owner of an item to maker of the highest bid
        User newOwner = optionalBid.get().getUser();
        soldItem.setUser(newOwner);   // change item owner
        itemDao.update(soldItem);
      }
      d.setStatus(false);   // close deal anyway
      dealDao.update(d);
    }
  }
}
