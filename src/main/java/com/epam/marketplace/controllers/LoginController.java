package com.epam.marketplace.controllers;

import com.epam.marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  private final UserService userService;

  @Autowired
  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/welcome", method = RequestMethod.GET)
  public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
    ModelAndView model = new ModelAndView();
    model.addObject("title", "- Welcome");
    if (error != null) {
      model.addObject("response", "Invalid login/password");
    } else {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentPrincipalName = authentication.getName();
    }
    model.setViewName("welcome");
    return model;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String welcome(Model model) {
    model.addAttribute("title", " - Welcome");
    return "welcome";
  }

}
