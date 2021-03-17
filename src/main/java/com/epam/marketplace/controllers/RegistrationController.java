package com.epam.marketplace.controllers;

import com.epam.marketplace.dto.DtoAssembler;
import com.epam.marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

  private final UserService userService;
  private final DtoAssembler dtoAssembler;

  @Autowired
  public RegistrationController(UserService userService, DtoAssembler dtoAssembler) {
    this.userService = userService;
    this.dtoAssembler = dtoAssembler;
  }

  @RequestMapping(value = "/registration", method = RequestMethod.GET)
  public String registration(Model model) {
    model.addAttribute("title", " - Registration");
    model.addAttribute("pageDisplayName","Registration");
    model.addAttribute("pageName","registration");
    model.addAttribute("currentUser", SecurityContextHolder.getContext().getAuthentication().getName());
    return "registration";
  }

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public String submit(
      @RequestParam(name = "login") String login,
      @RequestParam(name = "firstname") String firstname,
      @RequestParam(name = "lastname") String lastname,
      @RequestParam(name = "email") String email,
      @RequestParam(name = "password") String password,
      Model model
  ) {
    model.addAttribute("title", " - Registration");
    model.addAttribute("pageDisplayName","Registration");
    model.addAttribute("pageName","registration");
    model.addAttribute("currentUser", SecurityContextHolder.getContext().getAuthentication().getName());
    String response = "";
    boolean result = false;
    // VALIDATION
    if (userService.checkIfUserExistsByLogin(login)) {
      // user already exists!
      response = "Login '" + login + "' is occupied!";
    } else if (userService.checkIfEmailAlreadyRegistered(email)) {
      // email already registered!
      response = "User with email '" + email + "' is already registered!";
    } else {
      userService.createUser(dtoAssembler.newUser(login, password, email, firstname, lastname));
      response = "Successfully registered new user!";
      result = true;
    }
    model.addAttribute("response", response);
    model.addAttribute("result", result);
    return "registration";
  }
}
