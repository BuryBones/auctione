package com.epam.marketplace.controllers;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
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
public class NewItemController implements ValidityExceptionReporter {

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
    model.addAttribute("currentUser", userService.getCurrentUserName());
    return "new-item";
  }

  @RequestMapping(value = "/new-item/new", method = RequestMethod.POST)
  public String createNewItem(@Valid ItemDto itemDto, BindingResult result) {
    if ((result != null) && result.hasErrors()) {
      reportException(result);
    } else {
      itemService.createItem(itemDto);
      logger.info("ItemDto is valid");
    }
    return "redirect:/items";
  }
}
