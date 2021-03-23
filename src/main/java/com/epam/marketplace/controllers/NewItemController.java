package com.epam.marketplace.controllers;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.services.ItemService;
import com.epam.marketplace.services.UserService;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewItemController {

  private final Logger logger = Logger.getLogger("application");
  private final ItemService itemService;
  private final UserService userService;

  @Autowired
  public NewItemController(ItemService itemService, UserService userService) {
    this.itemService = itemService;
    this.userService = userService;
  }

  @RequestMapping(value = "/new-item", method = RequestMethod.GET)
  public String newItem(Model model) {
    model.addAttribute("title", " - New Item");
    model.addAttribute("pageDisplayName", "New Item");
    model.addAttribute("pageName", "new-item");
    model.addAttribute("currentUser", userService.getCurrentUserName());
    return "new-item";
  }

  @RequestMapping(value = "/new-item/new", method = RequestMethod.POST)
  public String createNewItem(@Valid ItemDto itemDto, BindingResult result) {
    // TODO: make some on-view notification about operation result for user
    if ((result != null) && result.hasErrors()) {
      StringBuilder responseBuilder = new StringBuilder();
      result.getAllErrors().forEach(e -> responseBuilder.append(e.getDefaultMessage() + "; "));
      logger.warning(responseBuilder.toString());
    } else {
      itemService.createItem(itemDto);
      logger.info("No errors found");
    }
    return "redirect:/items";
  }
}
