package com.epam.dao;

import java.util.Date;
import java.util.List;
import com.epam.entities.Deal;

public interface DealDao extends CommonDao<Deal> {

  List<Deal> getDealsByStatus(boolean status);
  List<Deal> getDealsFromDate(Date from);

}
