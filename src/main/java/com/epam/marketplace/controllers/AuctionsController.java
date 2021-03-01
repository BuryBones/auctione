package com.epam.marketplace.controllers;

import com.epam.marketplace.services.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuctionsController {

  @Autowired
  DefaultService defaultService;

  @RequestMapping(value = "/auctions", method = RequestMethod.GET)
  public String auctions(@RequestParam(name = "showDeals", defaultValue = "open") String show, Model model) {
    model.addAttribute("deals",defaultService.getAuctions(show));
    return "auctions";
  }

  @RequestMapping(value = "/auctions.ajax", method = RequestMethod.GET)
  public String auctionsAjax(@RequestParam(name = "showDeals", defaultValue = "open") String show, Model model) {
    model.addAttribute("deals",defaultService.getAuctions(show));
    return "auctions-table";
  }

}
