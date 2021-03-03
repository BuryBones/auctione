package com.epam.marketplace.controllers;

import com.epam.marketplace.dao.ItemDao;
import com.epam.marketplace.dao.impl.ItemDaoImpl;
import com.epam.marketplace.entities.Item;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemsController {

  @RequestMapping(value = "/items", method = RequestMethod.GET)
  public String items(Model model) {
    // TODO: make a service already!
    ItemDao itemDao = new ItemDaoImpl();
    List<Item> items = itemDao.findByUserId(7);
    model.addAttribute("items",items);
    return "items";
  }

  @RequestMapping(value = "/items.sell", method = RequestMethod.POST)
  public String sellItem(
      @RequestParam(name = "userId", required = true) int userId,
      @RequestParam(name = "itemId", required = true) int itemId,
      @RequestParam(name = "initPrice", required = true) String initPriceStr,
      @RequestParam(name = "stopDate", required = true) String stopDateStr,
      @RequestParam(name = "stopTime", required = true) String stopTimeStr,
      Model model) {
    /* TODO: what are going to be returned??? Text? HTML?
        What is to be shown on the view?
     */
    String response = String.format(
        "Got the following: User: %d; Item: %d; InitPrice: %s; StopDate: %s; StopTime: %s",
        userId,itemId,initPriceStr,stopDateStr,stopTimeStr);
    System.out.println(response);
    return response;
  }
}
