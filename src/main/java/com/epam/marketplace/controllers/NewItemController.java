package com.epam.marketplace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewItemController {

  @RequestMapping(value = "/new-item", method = RequestMethod.GET)
  public String newItem() {
    return "new-item";
  }
}
