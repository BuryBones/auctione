package com.epam.marketplace.internal;

import com.epam.marketplace.Constants;
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
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatusTask {

  private final Logger logger = Logger.getLogger("application");
  private final DealDao dealDao;
  private final ItemDao itemDao;

  @Autowired
  public StatusTask(DealDao dealDao, ItemDao itemDao) {
    this.dealDao = dealDao;
    this.itemDao = itemDao;
  }

  /**
   * Changes item's owner to the one who had made the highest bid, and changes status of the deal.
   * If there were no bids, keeps the same owner.
   */
  @Async
  @Scheduled(fixedRate = Constants.STATUS_REFRESH_RATE)
  public void execute() {
    logger.info("Launch status task");
    int ownerChangeCount = 0;
    int totalClosedCount = 0;

    for (Deal d : getOverdueDeals()) {
      Item soldItem = d.getItem();
      // find the highest bid if there were any
      Optional<Bid> optionalBid = d.getBids().stream().max(Comparator.comparing(Bid::getOffer));
      if (optionalBid.isPresent()) {
        User newOwner = optionalBid.get().getUser();
        soldItem.setUser(newOwner);   // change the owner of an item to the highest bid maker
        itemDao.update(soldItem);
        ownerChangeCount++;
      }
      // if there were no bids at all
      d.setStatus(false);   // close deal anyway
      dealDao.update(d);
      totalClosedCount++;
    }
    logger.info("Deals closed: " + totalClosedCount + "; Owners changed: " + ownerChangeCount);
    logger.info("Finish status task");
  }

  private List<Deal> getOverdueDeals() {
    List<Deal> openDeals = dealDao.findAllFullByStatus(true);   // find deals with unresolved status
    return openDeals.stream()
        .filter(d -> d.getCloseTime().isBefore(LocalDateTime.now()))  // check if deal is overdue
        .collect(Collectors.toList());
  }
}
