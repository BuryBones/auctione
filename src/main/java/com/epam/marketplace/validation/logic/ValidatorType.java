package com.epam.marketplace.validation.logic;

public enum ValidatorType {
  USER("userValidators"),
  ITEM("itemValidators"),
  DEAL("dealValidators"),
  BID("bidValidators");

  private final String name;

  ValidatorType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
