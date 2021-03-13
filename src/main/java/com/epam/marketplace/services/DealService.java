package com.epam.marketplace.services;

import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.dto.mappers.DealMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dealService")
public class DealService {

  private final DealDao dealDao;
  private final DealMapper dealMapper;

  @Autowired
  public DealService (DealDao dealDao, DealMapper dealMapper) {
    this.dealDao = dealDao;
    this.dealMapper = dealMapper;
  }

  public Long getAmount(String status) {
    return dealDao.findAmountByStatus(status);
  }

  public List<DealDto> getAuctions(String status, String sortBy, String sortMode, int currentPage, int pageSize) {
    boolean naturalOrder = sortMode.equals("asc");
    List<Deal> deals = dealDao.findAllFullWithLastBidByStatus(status, pageSize, currentPage, sortBy, naturalOrder);

    ArrayList<DealDto> result = new ArrayList<>(deals.size());
    for (Deal d: deals) {
      result.add(dealMapper.getDtoFromEntity(d));
    }
    return result;
  }

  public boolean createAuction(DealDto newborn) {
    Deal newDeal = dealMapper.getEntityFromDto(newborn);
    try {
      dealDao.save(newDeal);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }


}
