package com.epam.marketplace.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@PreAuthorize("") // seems that here must be the name of some method
//@RequestMapping(value = "/")
@Controller
public class LoginController {

  @RequestMapping(value = {"/","/welcome"}, method = RequestMethod.GET)
  public String login() {
    return "welcome";
  }

}
