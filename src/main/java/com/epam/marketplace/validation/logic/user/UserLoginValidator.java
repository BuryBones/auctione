package com.epam.marketplace.validation.logic.user;

import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
import com.epam.marketplace.services.UserService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class UserLoginValidator extends AbstractUserLogicValidator {

  private final Logger logger = Logger.getLogger("application");
  private UserService userService;

  @Autowired
  public void setUserService(@Lazy UserService userService) {
    this.userService = userService;
  }

  @Override
  public void validate(UserDto dto) throws ValidityException {
    // check if login already used
    if (userService.checkIfUserExistsByLogin(dto.getLogin())) {
      logger.warning("Not unique login: " + dto.getLogin());
      throw new ValidityException("Login '" + dto.getLogin() + "' is occupied!");
    }
  }
}
