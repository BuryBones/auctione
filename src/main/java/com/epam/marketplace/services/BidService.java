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

@Service("bidService")
public class BidService {

  private final Logger logger = Logger.getLogger("application");
  private final BidDao bidDao;
  private final CommonMapper mapper;
  private final UserService userService;

  @Autowired
  @Qualifier("bidValidators")
  private List<LogicValidator<? extends Dto>> validators;

  @Autowired
  public BidService(BidDao bidDao, CommonMapper mapper, UserService userService) {
    this.bidDao = bidDao;
    this.mapper = mapper;
    this.userService = userService;
  }

  public void createBid(BidDto newBorn) throws ValidityException {
    newBorn.setUserId(userService.getCurrentUserId());
    newBorn.setDateAndTime(new Date());
    for (LogicValidator<? extends Dto> validatorInterface : validators) {
      AbstractBidLogicValidator bidValidator = (AbstractBidLogicValidator) validatorInterface;
      logger.info("Validating with " + bidValidator.getClass().getName());
      bidValidator.validate(newBorn);
    }
    Bid newBid = mapper.getEntityFromDto(newBorn);
    bidDao.save(newBid);
  }
}
