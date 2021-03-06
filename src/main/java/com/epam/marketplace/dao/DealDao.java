package com.epam.marketplace.dao;

import com.epam.marketplace.entities.Deal;
import java.time.LocalDateTime;
import java.util.List;

public interface DealDao extends CommonDao<Deal> {

  default Class<Deal> getType() {
    return Deal.class;
  }

  List<Deal> findByStatus(boolean status);

  List<Deal> findFromDate(LocalDateTime from);

  List<Deal> findAllFull();

  List<Deal> findAllFullByStatus(boolean status);

  List<Deal> findAllFullWithLastBidByStatus(String status, int pageSize, int currentPage,
      String sortBy, boolean order);

  Long findAmountByStatus(String status);

  boolean checkIfAnyOpenDealsByItemId(int itemId);
}
