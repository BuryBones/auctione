package com.epam.marketplace.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String welcome(HttpSession httpSession) {
    return "redirect:/welcome";
  }

  @RequestMapping(value = "/welcome", method = RequestMethod.GET)
  public String login(
      @RequestParam(value = "error", required = false) String error,
      Model model) {
    if (error != null) {
      model.addAttribute("response", "Invalid login/password");
    }
    return "welcome";
  }
}
