package com.epam.marketplace.controllers;

import com.epam.marketplace.services.ItemService;
import com.epam.marketplace.services.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewItemController {

  @Autowired
  ItemService itemService;

  @RequestMapping(value = "/new-item", method = RequestMethod.GET)
  public String newItem() {
    return "new-item";
  }

  @RequestMapping(value = "/new-item.new", method = RequestMethod.POST)
  public String createNewItem(
      @RequestParam(name = "userId", required = true) int userId,
      @RequestParam(name = "name", required = true) String name,
      @RequestParam(name = "description", required = false) String description
  ) {

    String response = String.format(
        "Got the following: User: %d; Name: %s; Description: %s",
        userId,name,description);
    System.out.println(response);

    ItemDto newItem = new ItemDto(name,description,userId);
    itemService.createItem(newItem);
    return "items";
  }
}
