package com.epam.marketplace.controllers;

import com.epam.marketplace.services.BidService;
import com.epam.marketplace.services.DealService;
import com.epam.marketplace.services.dto.BidDto;
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

  @Autowired
  BidService bidService;

  @RequestMapping(value = "/auctions", method = RequestMethod.GET)
  public String auctions(
      @RequestParam(name = "status", defaultValue = "open") String status,
      @RequestParam(name = "sortBy", defaultValue = "stopDate") String sortBy,
      @RequestParam(name = "sortMode", defaultValue = "asc") String sortMode,
      Model model) {
    model.addAttribute("deals", dealService.getAuctions(status, sortBy, sortMode));
    return "auctions";
  }

  @RequestMapping(value = "/auctions.ajax", method = RequestMethod.GET)
  public String auctionsAjax(
      @RequestParam(name = "status", defaultValue = "open") String status,
      @RequestParam(name = "sortBy", defaultValue = "stopDate") String sortBy,
      @RequestParam(name = "sortMode", defaultValue = "asc") String sortMode,
      Model model) {
    model.addAttribute("deals", dealService.getAuctions(status, sortBy, sortMode));
    return "auctions-table";
  }

  @RequestMapping(value = "/auctions.bid", method = RequestMethod.POST)
  public String makeBid(
      @RequestParam(name = "status", defaultValue = "open") String status,
      @RequestParam(name = "sortBy", defaultValue = "stopDate") String sortBy,
      @RequestParam(name = "sortMode", defaultValue = "asc") String sortMode,
      @RequestParam(name = "userId") int userId,
      @RequestParam(name = "dealId") int dealId,
      @RequestParam(name = "offer") String offer,
      Model model
  ) {
    // TODO: remove creating an object out of controller!
    BidDto newBid = new BidDto(userId, dealId, offer);
    bidService.createBid(newBid);

    model.addAttribute("deals",dealService.getAuctions(status, sortBy, sortMode));

    return "auctions";
  }

}
