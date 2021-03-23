package com.epam.marketplace.exceptions.handlers;

import com.epam.marketplace.exceptions.validity.ValidityException;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class ValidityExceptionHandler {

  private final Logger logger = Logger.getLogger("application");

  @ExceptionHandler(ValidityException.class)
  public RedirectView handleValidityExceptions(Exception ex, RedirectAttributes attributes) {
    logger.warning(ex.getMessage());
    attributes.addFlashAttribute("message", ex.getMessage());
    return new RedirectView("/market/validity-error");
  }
}
