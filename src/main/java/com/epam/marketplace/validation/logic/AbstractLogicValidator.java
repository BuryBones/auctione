package com.epam.marketplace.validation.logic;

import com.epam.marketplace.dto.AbstractDto;
import java.util.Date;

public abstract class AbstractLogicValidator<T extends AbstractDto> implements LogicValidator<T> {

  // TODO: can be moved to global settings
  protected final long maxLag = 60000;  // millis
  protected final long minDelay = 60000 * 59; // millis

  /**
   * Verifies that given date does not lag behind the current date for more than given range,
   * and given date is not after the current date.
   * @param date to verify
   * @param range is the maximum difference in milliseconds
   */
  protected boolean verifyDateMaxLag(Date date, long range) {
    long now = new Date().getTime();
    long toCheck = date.getTime();
    return (now > toCheck) && ((now - toCheck) <= range);
  }

  protected boolean verifyDateMinDelay(Date date, long range) {
    long now = new Date().getTime();
    long toCheck = date.getTime();
    return (now < toCheck) && ((toCheck - now) >= range);
  }
}
