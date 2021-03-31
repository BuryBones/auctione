package com.epam.marketplace.services;

import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.dto.Dto;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.exceptions.validity.ValidityException;
import com.epam.marketplace.validation.logic.LogicValidator;
import com.epam.marketplace.validation.logic.deal.AbstractDealLogicValidator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DealService {

  private final Logger logger = Logger.getLogger("application");
  private final DealDao dealDao;
  private final CommonMapper mapper;
  private final UserService userService;

  private final List<LogicValidator<? extends Dto>> validators;

  @Autowired
  public DealService(DealDao dealDao, CommonMapper mapper, UserService userService,
      @Qualifier("dealValidators") List<LogicValidator<? extends Dto>> validators) {
    this.dealDao = dealDao;
    this.mapper = mapper;
    this.userService = userService;
    this.validators = validators;
  }

  public Long getAmount(String status) {
    return dealDao.findAmountByStatus(status);
  }

  public List<DealDto> getAuctions(String status, String sortBy, String sortMode, int currentPage,
      int pageSize) {
    boolean naturalOrder = sortMode.equals("asc");
    List<Deal> deals = dealDao
        .findAllFullWithLastBidByStatus(status, pageSize, currentPage, sortBy, naturalOrder);

    ArrayList<DealDto> result = new ArrayList<>(deals.size());
    for (Deal d : deals) {
      result.add(mapper.getDtoFromEntity(d));
    }
    return result;
  }

  public void createAuction(DealDto newborn) throws ValidityException {
    newborn.setSellerId(userService.getCurrentUserId());
    newborn.setStartDate(new Date());
    for (LogicValidator<? extends Dto> validatorInterface : validators) {
      AbstractDealLogicValidator dealValidator = (AbstractDealLogicValidator) validatorInterface;
      logger.info("Validating with " + dealValidator.getClass().getName());
      dealValidator.validate(newborn);
    }
    Deal newDeal = mapper.getEntityFromDto(newborn);
    dealDao.save(newDeal);
  }
}
