package com.epam.marketplace.services;

import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.dto.DealDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dealService")
public class DealService {

  private final DealDao dealDao;
  private final CommonMapper mapper;

  @Autowired
  public DealService (DealDao dealDao, CommonMapper mapper) {
    this.dealDao = dealDao;
    this.mapper = mapper;
  }

  public Long getAmount(String status) {
    return dealDao.findAmountByStatus(status);
  }

  public List<DealDto> getAuctions(String status, String sortBy, String sortMode, int currentPage, int pageSize) {
    boolean naturalOrder = sortMode.equals("asc");
    List<Deal> deals = dealDao.findAllFullWithLastBidByStatus(status, pageSize, currentPage, sortBy, naturalOrder);

    ArrayList<DealDto> result = new ArrayList<>(deals.size());
    for (Deal d: deals) {
      result.add(mapper.getDtoFromEntity(d));
    }
    return result;
  }

  public boolean createAuction(DealDto newborn) {
    Deal newDeal = mapper.getEntityFromDto(newborn);
    try {
      dealDao.save(newDeal);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }


}
