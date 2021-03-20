package com.epam.marketplace.validation.logic;

import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.OperationResult;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DealLogicValidator extends AbstractLogicValidator<DealDto> {

  private final Logger logger = Logger.getLogger("application");
  private final DealDao dealDao;

  @Autowired
  public DealLogicValidator(DealDao dealDao) {
    this.dealDao = dealDao;
  }

  @Override
  public OperationResult validate(DealDto dto) {
    // check if open time is close to now
    if (!verifyDateMaxLag(dto.getStartDate(), maxLag)) {
      logger.warning("Deal start date is too old: " + dto.getStartDate());
      return new OperationResult(false,
"The open time of the deal is too far in the past (more than " + maxLag + " milliseconds)");
    }
    // check if close date is more than minimum delay after now
    if (!verifyDateMinDelay(dto.getStopDate(), minDelay)) {
      logger.warning("Deal stop date is too soon: " + dto.getStopDate());
      return new OperationResult(false,
"The close time of the deal is too soon (less than " + maxLag + " milliseconds)");
    }
    // check if item is not on sale already
    if (dealDao.checkIfAnyOpenDealsByItemId(dto.getItemId())) {
      logger.warning("Item #" + dto.getItemId() + " is already on sale");
      return new OperationResult(false,"Item is already on sale");
    }
    return OperationResult.success();
  }
}
