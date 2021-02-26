package com.epam.marketplace.controllers;

import com.epam.marketplace.services.DefaultService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuctionsController {

  @RequestMapping(value = "/auctions", method = RequestMethod.GET)
  public String auctions(@RequestParam(name = "show-deals", defaultValue = "open") String show, Model model) {
    // TODO: make a bean
    DefaultService service = new DefaultService();
    model.addAttribute("deals",service.getAuctions(show));
    return "auctions";
  }

}
