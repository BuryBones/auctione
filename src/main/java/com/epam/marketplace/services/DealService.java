package com.epam.marketplace.services;

import com.epam.marketplace.OperationResult;
import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.validation.ConstraintsValidator;
import com.epam.marketplace.validation.logic.DealLogicValidator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dealService")
public class DealService {

  private final DealDao dealDao;
  private final CommonMapper mapper;
  private final ConstraintsValidator constraintsValidator;
  private final DealLogicValidator dealLogicValidator;

  @Autowired
  public DealService(DealDao dealDao, CommonMapper mapper,
      ConstraintsValidator constraintsValidator,
      DealLogicValidator dealLogicValidator) {
    this.dealDao = dealDao;
    this.mapper = mapper;
    this.constraintsValidator = constraintsValidator;
    this.dealLogicValidator = dealLogicValidator;
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

  public OperationResult createAuction(DealDto newborn) {
    OperationResult validationResult = validate(newborn);
    if (validationResult.getResult()) {
      Deal newDeal = mapper.getEntityFromDto(newborn);
      return dealDao.save(newDeal);
    } else {
      return validationResult;
    }
  }

  public OperationResult validate(DealDto dealDto) {
    OperationResult constraintsValidationResult = constraintsValidator.validate(dealDto);
    if (constraintsValidationResult.getResult()) {
      return dealLogicValidator.validate(dealDto);
    } else {
      return constraintsValidationResult;
    }
  }
}
