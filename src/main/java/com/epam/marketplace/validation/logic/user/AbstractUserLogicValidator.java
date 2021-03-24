package com.epam.marketplace.validation.logic.user;

import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.validation.logic.AbstractLogicValidator;
import com.epam.marketplace.validation.logic.ValidatorType;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractUserLogicValidator extends AbstractLogicValidator<UserDto> {

  protected final ValidatorType type = ValidatorType.USER;

  @Override
  public ValidatorType getType() {
    return type;
  }
}
