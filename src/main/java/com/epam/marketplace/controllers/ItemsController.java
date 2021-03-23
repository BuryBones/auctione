package com.epam.marketplace.controllers;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
import com.epam.marketplace.services.DealService;
import com.epam.marketplace.dto.DtoAssembler;
import com.epam.marketplace.services.ItemService;
import com.epam.marketplace.services.UserService;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemsController {

  private final Logger logger = Logger.getLogger("application");
  private final ItemService itemService;
  private final DealService dealService;
  private final UserService userService;
  private final DtoAssembler dtoAssembler;

  @Autowired
  public ItemsController(ItemService itemService, DealService dealService, UserService userService,
      DtoAssembler dtoAssembler) {
    this.itemService = itemService;
    this.dealService = dealService;
    this.userService = userService;
    this.dtoAssembler = dtoAssembler;
  }

  @RequestMapping(value = "/items", method = RequestMethod.GET)
  public String items(Model model) {
    model.addAttribute("title", " - Items");
    model.addAttribute("pageDisplayName", "Items");
    model.addAttribute("pageName", "items");
    model.addAttribute("currentUser", userService.getCurrentUserName());
    List<ItemDto> items = itemService.getItemsByUserId(userService.getCurrentUserId());
    model.addAttribute("items", items);
    return "items";
  }

  @RequestMapping(value = "/items/sell", method = RequestMethod.POST)
  public String sellItem(
      @RequestParam(name = "itemId") int itemId,
      @RequestParam(name = "initPrice") String initPriceStr,
      @RequestParam(name = "stopDate") String stopDateStr,
      @RequestParam(name = "stopTime") String stopTimeStr
  ) {
    // TODO: make some on-view notification about operation result for user
    try {
      dealService.createAuction(
          dtoAssembler.newDealDto(
              userService.getCurrentUserId(), itemId, initPriceStr, stopDateStr, stopTimeStr));
    } catch (ParseException | ValidityException e) {
      // TODO: do smth with exception handling
      logger.severe(e.getMessage());
    }
    return "redirect:/auctions";
  }
}
