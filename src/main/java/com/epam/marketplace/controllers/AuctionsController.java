package com.epam.marketplace.controllers;

import com.epam.marketplace.services.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuctionsController {

  @Autowired
  DealService dealService;

  @RequestMapping(value = "/auctions", method = RequestMethod.GET)
  public String auctions(
      @RequestParam(name = "status", defaultValue = "open") String status,
      @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
      @RequestParam(name = "sortMode", defaultValue = "asc") String sortMode,
//      @RequestParam(name = "page", defaultValue = "1") int page,
      Model model) {
    model.addAttribute("deals", dealService.getAuctions(status, sortBy, sortMode));
    return "auctions";
  }

  @RequestMapping(value = "/auctions.ajax", method = RequestMethod.GET)
  public String auctionsAjax(
      @RequestParam(name = "status", defaultValue = "open") String status,
      @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
      @RequestParam(name = "sortMode", defaultValue = "asc") String sortMode,
//      @RequestParam(name = "page", defaultValue = "1") int page,
      Model model) {
    model.addAttribute("deals", dealService.getAuctions(status, sortBy, sortMode));
    return "auctions-table";
  }

}
