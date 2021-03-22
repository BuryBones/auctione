package com.epam.marketplace;

public class OperationResult {

  private final String message;
  private final boolean result;

  public static OperationResult success() {
    return new OperationResult(true, "Success");
  }

  public static OperationResult failure() {
    return new OperationResult(false, "failure");
  }

  public OperationResult(boolean result, String message) {
    this.result = result;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public boolean getResult() {
    return result;
  }
}
