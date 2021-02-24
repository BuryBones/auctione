package com.epam.marketplace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuctionsController {

  @RequestMapping(value = "/auctions", method = RequestMethod.GET)
  public String auctions() {
    return "auctions";
  }

}
