package com.epam.marketplace.controllers;

import com.epam.marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

  private final UserService userService;

  @Autowired
  public AdminController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String admin(Model model) {
    model.addAttribute("title", " - Admin Page");
    model.addAttribute("pageDisplayName","Admin Page");
    model.addAttribute("pageName","admin");
    model.addAttribute("currentUser", userService.getCurrentUserName());
    model.addAttribute("users", userService.getUsers());
    return "admin";
  }
}
