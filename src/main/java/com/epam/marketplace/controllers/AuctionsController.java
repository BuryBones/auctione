package com.epam.marketplace.controllers;

import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
import com.epam.marketplace.services.BidService;
import com.epam.marketplace.services.DealService;
import com.epam.marketplace.services.UserService;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuctionsController {

  private final Logger logger = Logger.getLogger("application");
  private final DealService dealService;
  private final BidService bidService;
  private final UserService userService;

  @Autowired
  public AuctionsController(
      DealService dealService,
      BidService bidService,
      UserService userService) {
    this.dealService = dealService;
    this.bidService = bidService;
    this.userService = userService;
  }

  @RequestMapping(value = "/auctions", method = RequestMethod.GET)
  public String auctions(
      @RequestParam(name = "status", defaultValue = "open") String status,
      @RequestParam(name = "sortBy", defaultValue = "stopDate") String sortBy,
      @RequestParam(name = "sortMode", defaultValue = "asc") String sortMode,
      @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
      @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
      Model model) {
    model.addAttribute("currentUser", userService.getCurrentUserName());
    model.addAttribute("deals",
        dealService.getAuctions(status, sortBy, sortMode, currentPage, pageSize));
    long amount = dealService.getAmount(status);
    int totalPages = (int) Math.ceil((float) amount / pageSize);
    model.addAttribute("status", status);
    model.addAttribute("sortBy", sortBy);
    model.addAttribute("sortMode", sortMode);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("userId", userService.getCurrentUserId());
    return "auctions";
  }

  @RequestMapping(value = "/auctions/ajax", method = RequestMethod.GET)
  public String auctionsAjax(
      @RequestParam(name = "status", defaultValue = "open") String status,
      @RequestParam(name = "sortBy", defaultValue = "stopDate") String sortBy,
      @RequestParam(name = "sortMode", defaultValue = "asc") String sortMode,
      @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
      @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
      Model model) {
    model.addAttribute("deals",
        dealService.getAuctions(status, sortBy, sortMode, currentPage, pageSize));
    long amount = dealService.getAmount(status);
    int totalPages = (int) Math.ceil((float) amount / pageSize);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("userId", userService.getCurrentUserId());
    return "auctions-table";
  }

  // TODO: make this interaction non-ajax?
  @RequestMapping(value = "/auctions/bid", method = RequestMethod.POST)
  public void makeBid(@Valid BidDto bidDto, BindingResult result) {
    if ((result != null) && result.hasErrors()) {
      StringBuilder responseBuilder = new StringBuilder();
      result.getAllErrors().forEach(e -> responseBuilder.append(e.getDefaultMessage() + "; "));
      throw new ValidityException(responseBuilder.toString());
    } else {
      bidService.createBid(bidDto);
      logger.info("No errors found");
    }
  }
}
