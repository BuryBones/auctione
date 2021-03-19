package com.epam.marketplace.validation.logic;

import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dto.DealDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DealLogicValidator extends AbstractLogicValidator<DealDto> {

  private final DealDao dealDao;

  @Autowired
  public DealLogicValidator(DealDao dealDao) {
    this.dealDao = dealDao;
  }

  @Override
  public String validate(DealDto dto) {
    // check if open time is close to now
    if (!verifyDateMaxLag(dto.getStartDate(), maxLag)) {
      return "The open time of the deal is too far in the past (more than " + maxLag + " milliseconds)";
    }
    // check if close date is more than minimum delay after now
    if (!verifyDateMinDelay(dto.getStopDate(), minDelay)) {
      return "The close time of the deal is too soon (less than " + maxLag + " milliseconds)";
    }
    // check if item is not on sale already
    if (dealDao.checkIfAnyOpenDealsByItemId(dto.getItemId())) {
      return "Item is already on sale";
    }
    return "ok";
  }
}
