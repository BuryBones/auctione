package com.epam.marketplace.controllers;

import com.epam.marketplace.exceptions.validity.ValidityException;
import org.springframework.validation.BindingResult;

public interface ValidityExceptionReporter {

  default void reportException(BindingResult result) {
    StringBuilder responseBuilder = new StringBuilder();
    result.getAllErrors().forEach(e -> responseBuilder.append(e.getDefaultMessage() + "; "));
    throw new ValidityException(responseBuilder.toString());
  }
}
