package com.epam.marketplace.dao;

import java.time.LocalDateTime;
import java.util.List;
import com.epam.marketplace.entities.Deal;

public interface DealDao extends CommonDao<Deal> {

  default Class<Deal> getType() {
    return Deal.class;
  }

  List<Deal> findByStatus(boolean status);
  List<Deal> findFromDate(LocalDateTime from);
  List<Deal> findAllFull();
  List<Deal> findAllFullByStatus(boolean status);

}
