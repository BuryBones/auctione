package com.epam.marketplace.services;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
import com.epam.marketplace.validation.logic.ValidatorType;
import com.epam.marketplace.validation.logic.bid.AbstractBidLogicValidator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bidService")
public class BidService {

  private final Logger logger = Logger.getLogger("application");
  private final BidDao bidDao;
  private final CommonMapper mapper;
  private final BeanFactory beanFactory;
  private final UserService userService;
  private List<AbstractBidLogicValidator> validators;

  @Autowired
  public BidService(BidDao bidDao, CommonMapper mapper,
      BeanFactory beanFactory, UserService userService) {
    this.bidDao = bidDao;
    this.mapper = mapper;
    this.beanFactory = beanFactory;
    this.userService = userService;
  }

  public void createBid(BidDto newBorn) throws ValidityException {
    newBorn.setUserId(userService.getCurrentUserId());
    newBorn.setDateAndTime(new Date());
    for (AbstractBidLogicValidator validator : validators) {
      logger.info("Validating with " + validator.getClass().getName());
      validator.validate(newBorn);
    }
    Bid newBid = mapper.getEntityFromDto(newBorn);
    bidDao.save(newBid);
  }

  @PostConstruct
  private void initValidators() {
    validators = (List<AbstractBidLogicValidator>)
        beanFactory.getBean("validators", ValidatorType.BID);
    logger.info("Bid service got " + validators.size() + " validators");
  }
}
