package com.epam.marketplace.services;

import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.dto.Dto;
import com.epam.marketplace.dto.Pagination;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.exceptions.validity.ValidityException;
import com.epam.marketplace.validation.logic.LogicValidator;
import com.epam.marketplace.validation.logic.deal.AbstractDealLogicValidator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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

  public List<DealDto> getAuctions(Pagination pagination) {
    setTotalPages(pagination);
    boolean naturalOrder = pagination.getSortMode().equals("asc");
    List<Deal> deals = dealDao
        .findAllFullWithLastBidByStatus(
            pagination.getStatus(),
            pagination.getPageSize(),
            pagination.getCurrentPage(),
            pagination.getSortBy(),
            naturalOrder);
    //    ArrayList<DealDto> result = new ArrayList<>(deals.size());
    //    for (Deal d : deals) {
    //      result.add(mapper.getDtoFromEntity(d));
    //    }
    return deals.stream().map(mapper::getDtoFromEntity).collect(Collectors.toList());
  }

  public void createAuction(DealDto newBorn) throws ValidityException {
    setNewbornFields(newBorn);
    validate(newBorn);
    dealDao.save(mapper.getEntityFromDto(newBorn));
  }

  private void setTotalPages(Pagination pagination) {
    long amount = getAmount(pagination.getStatus());
    int totalPages = (int) Math.ceil((float) amount / pagination.getPageSize());
    pagination.setTotalPages(totalPages);
  }

  private void setNewbornFields(DealDto newBorn) {
    newBorn.setSellerId(userService.getCurrentUserId());
    newBorn.setStartDate(new Date());
  }

  private void validate(DealDto newBorn) throws ValidityException {
    for (LogicValidator<? extends Dto> validatorInterface : validators) {
      AbstractDealLogicValidator dealValidator = (AbstractDealLogicValidator) validatorInterface;
      logger.info("Validating with " + dealValidator.getClass().getName());
      dealValidator.validate(newBorn);
    }
  }
}
