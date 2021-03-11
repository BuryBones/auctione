package com.epam.marketplace.controllers;

import com.epam.marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = {"/","/welcome"}, method = RequestMethod.GET)
  public String welcome(Model model) {
    model.addAttribute("title", " - Welcome");
    return "welcome";
  }

  @RequestMapping(value = {"/","/welcome"}, method = RequestMethod.POST)
  public String login(
      @RequestParam(name = "login") String login,
      @RequestParam(name = "password") String password,
      Model model
  ) {
    model.addAttribute("title", " - Welcome");

    // VALIDATION
    String response;
    if (userService.checkIfUserExistsByLogin(login)) {
      // user exists
      if (userService.checkCredentials(login,password)) {
        // success
        return "redirect:/auctions";
      } else {
        // wrong credentials
        response = "Invalid login/password";
      }
    } else {
      // user does not exist
      response = "Login not found!";
    }
    model.addAttribute("response", response);
    return "welcome";
  }

}
