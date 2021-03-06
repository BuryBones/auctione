package com.epam.marketplace.controllers;

import com.epam.marketplace.dto.DtoAssembler;
import com.epam.marketplace.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewItemController {

  @Autowired
  private ItemService itemService;

  @Autowired
  private DtoAssembler dtoAssembler;

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
    itemService.createItem(dtoAssembler.newItem(userId,name,description));
    return "redirect:/items";
  }
}
