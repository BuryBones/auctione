package com.epam.marketplace.controllers;

import com.epam.marketplace.dto.DtoAssembler;
import com.epam.marketplace.services.ItemService;
import com.epam.marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewItemController {

  private final ItemService itemService;
  private final UserService userService;
  private final DtoAssembler dtoAssembler;

  @Autowired
  public NewItemController(ItemService itemService, UserService userService, DtoAssembler dtoAssembler) {
    this.itemService = itemService;
    this.userService = userService;
    this.dtoAssembler = dtoAssembler;
  }

  @RequestMapping(value = "/new-item", method = RequestMethod.GET)
  public String newItem(Model model) {
    model.addAttribute("title", " - New Item");
    model.addAttribute("pageDisplayName","New Item");
    model.addAttribute("pageName","new-item");
    model.addAttribute("currentUser", userService.getCurrentUserName());
    return "new-item";
  }

  @RequestMapping(value = "/new-item/new", method = RequestMethod.POST)
  public String createNewItem(
      @RequestParam(name = "name") String name,
      @RequestParam(name = "description", required = false, defaultValue = "") String description
  ) {
    itemService.createItem(dtoAssembler.newItem(userService.getCurrentUserId(), name,description));
    return "redirect:/items";
  }
}
