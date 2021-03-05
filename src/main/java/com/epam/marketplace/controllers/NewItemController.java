package com.epam.marketplace.controllers;

import com.epam.marketplace.services.ItemService;
import com.epam.marketplace.services.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class NewItemController {

  @Autowired
  private ItemService itemService;

  @RequestMapping(value = "/new-item", method = RequestMethod.GET)
  public String newItem() {
    return "new-item";
  }

  @RequestMapping(value = "/new-item.new", method = RequestMethod.POST)
  public String createNewItem(
      @RequestParam(name = "userId") int userId,
      @RequestParam(name = "name") String name,
      @RequestParam(name = "description", required = false, defaultValue = "") String description
  ) {
    // TODO: remove creating an object out of controller!
    ItemDto newItem = new ItemDto(name,description,userId);
    itemService.createItem(newItem);
    return "items";
  }
}
