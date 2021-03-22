package com.epam.marketplace.controllers;

import com.epam.marketplace.OperationResult;
import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.services.UserService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

  private final Logger logger = Logger.getLogger("application");
  private final UserService userService;

  @Autowired
  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/registration", method = RequestMethod.GET)
  public String registration(Model model) {
    model.addAttribute("title", " - Registration");
    model.addAttribute("pageDisplayName","Registration");
    model.addAttribute("pageName","registration");
    return "registration";
  }

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public String submit(
      UserDto userDto, Model model
  ) {
    model.addAttribute("title", " - Registration");
    model.addAttribute("pageDisplayName","Registration");
    model.addAttribute("pageName","registration");

    OperationResult result = userService.createUser(userDto);
    logger.info("New user registration result: " + result.getMessage());

    model.addAttribute("response", result.getMessage());
    model.addAttribute("result", result.getResult());
    return "registration";
  }
}
