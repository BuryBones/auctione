package com.epam.marketplace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

  @GetMapping(value = "/validity-error")
  public String error(Model model) {
    return "validity-error";
  }
}
