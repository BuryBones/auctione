package com.epam.marketplace.controllers;

import com.epam.marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String welcome() {
    return "redirect:/welcome";
  }

  @RequestMapping(value = "/welcome", method = RequestMethod.GET)
  public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
    // TODO: can I get ModelAndView as an argument?
    ModelAndView model = new ModelAndView();
    model.addObject("title", "- Welcome");
    model.addObject("pageDisplayName","Welcome to AuctiOne");
    model.addObject("pageName","welcome");
    model.setViewName("welcome");
    if (error != null) {
      model.addObject("response", "Invalid login/password");
    }
    return model;
  }
}
