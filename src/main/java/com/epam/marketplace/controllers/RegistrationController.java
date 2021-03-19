package com.epam.marketplace.controllers;

import com.epam.marketplace.dto.DtoAssembler;
import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.services.UserService;
import com.epam.marketplace.validation.ConstraintsValidator;
import com.epam.marketplace.validation.logic.UserLogicValidator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Validated
public class RegistrationController {

  private final UserService userService;
  private final ConstraintsValidator constraintsValidator;
  private final UserLogicValidator userLogicValidator;

  @Autowired
  public RegistrationController(UserService userService,
      ConstraintsValidator constraintsValidator,
      UserLogicValidator userLogicValidator) {
    this.userService = userService;
    this.constraintsValidator = constraintsValidator;
    this.userLogicValidator = userLogicValidator;
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

    // TODO: make service validate everything, and controller get the response only
//    constraintsValidator.validate(userDto);
//    if (userLogicValidator.validate(userDto).equals("ok")) {
//      userService.createUser(userDto);
//    }

//    model.addAttribute("response", response);
//    model.addAttribute("result", result);
    return "registration";
  }
}
