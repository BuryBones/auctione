package com.epam.marketplace.validation.logic.deal;

import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DealNotDoubledValidator extends AbstractDealLogicValidator {

  private final Logger logger = Logger.getLogger("application");
  private final DealDao dealDao;

  @Autowired
  public DealNotDoubledValidator(DealDao dealDao) {
    this.dealDao = dealDao;
  }

  @Override
  public void validate(DealDto dto) throws ValidityException {
    // check if item is not on sale already
    if (dealDao.checkIfAnyOpenDealsByItemId(dto.getItemId())) {
      logger.warning("Item #" + dto.getItemId() + " is already on sale");
      throw new ValidityException("Item is already on sale");
    }
  }
}
