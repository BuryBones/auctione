package com.epam.marketplace.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("") // seems that here must be the name of some method
@RequestMapping(name = "login")
@Controller
public class LoginController {



}
