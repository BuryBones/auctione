package com.epam.marketplace.exceptions;

public class MarketplaceException extends RuntimeException {

  public MarketplaceException(String message) {
    super(message);
  }

  public MarketplaceException(String message, Throwable cause) {
    super(message, cause);
  }
}
