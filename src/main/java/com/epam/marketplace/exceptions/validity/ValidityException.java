package com.epam.marketplace.exceptions.validity;

import com.epam.marketplace.exceptions.MarketplaceException;

public class ValidityException extends MarketplaceException {

  public ValidityException(String message) {
    super(message);
  }

  public ValidityException(String message, Throwable cause) {
    super(message, cause);
  }
}
