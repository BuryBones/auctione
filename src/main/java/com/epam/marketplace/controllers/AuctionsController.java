package com.epam.marketplace.controllers;

import com.epam.marketplace.services.BidService;
import com.epam.marketplace.services.DealService;
import com.epam.marketplace.dto.DtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuctionsController {

  @Autowired
  private DealService dealService;

  @Autowired
  private BidService bidService;

  @Autowired
  private DtoAssembler dtoAssembler;

  @RequestMapping(value = "/auctions", method = RequestMethod.GET)
  public String auctions(
      @RequestParam(name = "status", defaultValue = "open") String status,
      @RequestParam(name = "sortBy", defaultValue = "stopDate") String sortBy,
      @RequestParam(name = "sortMode", defaultValue = "asc") String sortMode,
      @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
      @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
      Model model) {
    model.addAttribute("title", " - Deals");
    model.addAttribute("deals", dealService.getAuctions(status, sortBy, sortMode, currentPage, pageSize));
    long amount = dealService.getAmount(status);
    model.addAttribute("totalPages", Math.ceil((float)(amount/pageSize)));
    model.addAttribute("currentPage", currentPage);
    return "auctions";
  }

  @RequestMapping(value = "/auctions.ajax", method = RequestMethod.GET)
  public String auctionsAjax(
      @RequestParam(name = "status", defaultValue = "open") String status,
      @RequestParam(name = "sortBy", defaultValue = "stopDate") String sortBy,
      @RequestParam(name = "sortMode", defaultValue = "asc") String sortMode,
      @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
      @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
      Model model) {
    model.addAttribute("deals", dealService.getAuctions(status, sortBy, sortMode, currentPage, pageSize));
    long amount = dealService.getAmount(status);
    model.addAttribute("totalPages", Math.ceil((float)(amount/pageSize)));
    model.addAttribute("currentPage", currentPage);
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
      @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
      @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
      Model model
  ) {
    bidService.createBid(dtoAssembler.newBid(userId, dealId, offer));
    model.addAttribute("deals",dealService.getAuctions(status, sortBy, sortMode, currentPage, pageSize));
    return "auctions";
  }

}
