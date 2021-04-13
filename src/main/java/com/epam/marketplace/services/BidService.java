package com.epam.marketplace.services;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.dto.Dto;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.exceptions.validity.ValidityException;
import com.epam.marketplace.validation.logic.LogicValidator;
import com.epam.marketplace.validation.logic.bid.AbstractBidLogicValidator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BidService {

  private final Logger logger = Logger.getLogger("application");
  private final BidDao bidDao;
  private final CommonMapper mapper;
  private final UserService userService;

  private final List<LogicValidator<? extends Dto>> validators;

  @Autowired
  public BidService(BidDao bidDao, CommonMapper mapper, UserService userService,
      @Qualifier("bidValidators") List<LogicValidator<? extends Dto>> validators) {
    this.bidDao = bidDao;
    this.mapper = mapper;
    this.userService = userService;
    this.validators = validators;
  }

  public void createBid(BidDto newBorn) throws ValidityException {
    setNewbornFields(newBorn);
    validate(newBorn);
    bidDao.save(mapper.getEntityFromDto(newBorn));
  }

  private void setNewbornFields(BidDto newBorn) {
    newBorn.setUserId(userService.getCurrentUserId());
    newBorn.setDateAndTime(new Date());
  }

  private void validate(BidDto newBorn) throws ValidityException {
    for (LogicValidator<? extends Dto> validatorInterface : validators) {
      AbstractBidLogicValidator bidValidator = (AbstractBidLogicValidator) validatorInterface;
      logger.info("Validating with " + bidValidator.getClass().getName());
      bidValidator.validate(newBorn);
    }
  }
}
