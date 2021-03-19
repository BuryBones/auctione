package com.epam.marketplace.validation.logic;

import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserLogicValidator extends AbstractLogicValidator<UserDto>{

  private UserService userService;

  @Override
  public String validate(UserDto dto) {
    // check if login already used
    if (userService.checkIfUserExistsByLogin(dto.getLogin())) {
      return "Login '" + dto.getLogin() + "' is occupied!";
    }
    // check if email already used
    if (userService.checkIfEmailAlreadyRegistered(dto.getEmail())) {
      return "User with email '" + dto.getEmail() + "' is already registered!";
    }
    return "ok";
  }
}
