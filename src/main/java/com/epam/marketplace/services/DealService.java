package com.epam.marketplace.services;

import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
import com.epam.marketplace.validation.logic.ValidatorType;
import com.epam.marketplace.validation.logic.deal.AbstractDealLogicValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dealService")
public class DealService {

  private final Logger logger = Logger.getLogger("application");
  private final DealDao dealDao;
  private final CommonMapper mapper;
  private List<AbstractDealLogicValidator> validators;

  private final BeanFactory beanFactory;

  @Autowired
  public DealService(DealDao dealDao, CommonMapper mapper, BeanFactory beanFactory) {
    this.dealDao = dealDao;
    this.mapper = mapper;
    this.beanFactory = beanFactory;
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

  public void createAuction(DealDto newborn) throws ValidityException {
    for (AbstractDealLogicValidator validator: validators) {
      logger.info("Validating with " + validator.getClass().getName());
      validator.validate(newborn);
    }
    Deal newDeal = mapper.getEntityFromDto(newborn);
    dealDao.save(newDeal);
  }

  @PostConstruct
  private void initValidators() {
    validators = (List<AbstractDealLogicValidator>)
        beanFactory.getBean("validators",ValidatorType.DEAL);
    logger.info("Deal service got " + validators.size() + " validators");
  }
}
