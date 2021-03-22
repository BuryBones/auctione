package com.epam.marketplace.validation.logic;

import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.services.UserService;
import com.epam.marketplace.OperationResult;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLogicValidator extends AbstractLogicValidator<UserDto>{

  private final Logger logger = Logger.getLogger("application");
  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public OperationResult validate(UserDto dto) {
    // check if login already used
    if (userService.checkIfUserExistsByLogin(dto.getLogin())) {
      logger.warning("Not unique login: " + dto.getLogin());
      return new OperationResult(false,"Login '" + dto.getLogin() + "' is occupied!");
    }
    // check if email already used
    if (userService.checkIfEmailAlreadyRegistered(dto.getEmail())) {
      logger.warning("Not unique email: " + dto.getEmail());
      return new OperationResult(false,"User with email '" + dto.getEmail() + "' is already registered!");
    }
    return OperationResult.success();
  }
}
