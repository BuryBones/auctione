package com.epam.marketplace.controllers;

import com.epam.marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String admin(Model model) {
    model.addAttribute("title", " - Admin Page");
    model.addAttribute("users", userService.getUsers());
    return "admin";
  }
}
