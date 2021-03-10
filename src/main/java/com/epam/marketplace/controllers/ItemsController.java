package com.epam.marketplace.controllers;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.services.DealService;
import com.epam.marketplace.dto.DtoAssembler;
import com.epam.marketplace.services.ItemService;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemsController {

  @Autowired
  private ItemService itemService;

  @Autowired
  private DealService dealService;

  @Autowired
  private DtoAssembler dtoAssembler;

  @RequestMapping(value = "/items", method = RequestMethod.GET)
  public String items(Model model) {
    model.addAttribute("title", " - Items");

    int userId = 7; // Magic number

    List<ItemDto> items = itemService.getItemsByUserId(7);
    model.addAttribute("items",items);
    return "items";
  }

  @RequestMapping(value = "/items.sell", method = RequestMethod.POST)
  public String sellItem(
      @RequestParam(name = "userId") int userId,
      @RequestParam(name = "itemId") int itemId,
      @RequestParam(name = "initPrice") String initPriceStr,
      @RequestParam(name = "stopDate") String stopDateStr,
      @RequestParam(name = "stopTime") String stopTimeStr,
      Model model) {
    try {
      dealService.createAuction(dtoAssembler.newDeal(userId,itemId,initPriceStr,stopDateStr,stopTimeStr));
    } catch (ParseException e) {
      // TODO: do smth with exception handling
      e.printStackTrace();
    }
    return "redirect:/auctions";
  }
}
