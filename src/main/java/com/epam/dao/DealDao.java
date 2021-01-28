package com.epam.dao;

import java.util.Date;
import java.util.List;
import com.epam.entities.Deal;

public interface DealDao extends CommonDao<Deal> {

  default Class<Deal> getType() {
    return Deal.class;
  }

  List<Deal> findByStatus(boolean status);
  List<Deal> findFromDate(Date from);

}
