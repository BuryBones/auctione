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

  @Autowired
  private DealDao dealDao;
  @Autowired
  private DealMapper dealMapper;

  public Long getAmount(String status) {
    return switch (status) {
      case "open" -> dealDao.findAmountByStatus(true);
      case "closed" -> dealDao.findAmountByStatus(false);
      case "all" -> dealDao.findAmount();
      default -> dealDao.findAmount();
    };
  }

  public List<DealDto> getAuctions(String status, String sortBy, String sortMode, int currentPage, int pageSize) {
    boolean naturalOrder = sortMode.equals("asc");
    List<Deal> deals = switch (status) {
      case "open" ->
          dealDao.findAllFullWithLastBidByStatus(true, pageSize, currentPage, sortBy, naturalOrder);
      case "closed" ->
          dealDao.findAllFullWithLastBidByStatus(false, pageSize, currentPage, sortBy, naturalOrder);
      case "all" ->
          dealDao.findAllFullWithLastBid(pageSize, currentPage, sortBy, naturalOrder);
      default -> Collections.emptyList();
    };

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
