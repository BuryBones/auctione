package com.epam.marketplace.controllers;

import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
import com.epam.marketplace.services.DealService;
import com.epam.marketplace.services.ItemService;
import com.epam.marketplace.services.UserService;
import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ItemsController {

  private final Logger logger = Logger.getLogger("application");
  private final ItemService itemService;
  private final DealService dealService;
  private final UserService userService;

  @Autowired
  public ItemsController(ItemService itemService, DealService dealService, UserService userService) {
    this.itemService = itemService;
    this.dealService = dealService;
    this.userService = userService;
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
  public String sellItem(@Valid DealDto dealDto, BindingResult result) {
    if ((result != null) && result.hasErrors()) {
      StringBuilder responseBuilder = new StringBuilder();
      result.getAllErrors().forEach(e -> responseBuilder.append(e.getDefaultMessage() + "; "));
      throw new ValidityException(responseBuilder.toString());
    } else {
      dealService.createAuction(dealDto);
      logger.info("No errors found");
    }
    return "redirect:/auctions";
  }
}
