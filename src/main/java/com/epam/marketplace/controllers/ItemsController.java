package com.epam.marketplace.controllers;

import com.epam.marketplace.dao.ItemDao;
import com.epam.marketplace.dao.impl.ItemDaoImpl;
import com.epam.marketplace.entities.Item;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ItemsController {

  @RequestMapping(value = "/items", method = RequestMethod.GET)
  public String items(Model model) {
    ItemDao itemDao = new ItemDaoImpl();
    List<Item> items = itemDao.findByUserId(7);
    model.addAttribute("items",items);
    return "items";
  }
}
