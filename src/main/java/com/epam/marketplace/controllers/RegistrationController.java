package com.epam.marketplace.controllers;

import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
import com.epam.marketplace.services.UserService;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController implements ValidityExceptionReporter {

  private final Logger logger = Logger.getLogger("application");
  private final UserService userService;

  @Autowired
  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/registration", method = RequestMethod.GET)
  public String registration() {
    return "registration";
  }

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public String submit(@Valid UserDto userDto, BindingResult result, Model model) {
    if ((result != null) && result.hasErrors()) {
     reportException(result);
    } else {
      userService.createUser(userDto);
      logger.info("UserDto is valid");
      model.addAttribute("response", "Success");
      model.addAttribute("result", true);
    }
    return "registration";
  }
}
