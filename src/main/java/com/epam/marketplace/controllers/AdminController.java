package com.epam.marketplace.controllers;

import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.dao.impl.UserDaoImpl;
import com.epam.marketplace.entities.User;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String admin(Model model) {
    UserDao userDao = new UserDaoImpl();
    List<User> users = userDao.findAllWithRoles();
    model.addAttribute("users",users);
    return "admin";
  }
}
