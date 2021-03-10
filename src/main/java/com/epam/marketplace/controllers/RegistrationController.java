package com.epam.marketplace.controllers;

import com.epam.marketplace.dto.DtoAssembler;
import com.epam.marketplace.services.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

  @Autowired
  private UserService userService;

  @Autowired
  private DtoAssembler dtoAssembler;

  @RequestMapping(value = "/registration", method = RequestMethod.GET)
  public String registration() {
    return "registration";
  }

  // TODO: Model and view
  @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = "text/plain")
  public String submit(
      @RequestBody String postPayLoad
//      @RequestBody UserDto user
  ) {
    System.out.println("Got the following:\r\n" + postPayLoad);
    Map<String, String> params = new HashMap<>();
    String[] entry = postPayLoad.split("&");
    for (String s: entry) {
      String[] pair = s.split("=");
      params.put(pair[0].trim(),pair[1].trim());
    }
    // VALIDATE
    if (userService.checkIfUserExistsByLogin(params.get("login"))) {
      return "registration";
    } else {
      userService.createUser(dtoAssembler.newUser(
          params.get("login"),
          params.get("password"),
          params.get("email"),
          params.get("firstName"),
          params.get("lastName")
      ));
      return "redirect:/welcome";
    }
  }
}
