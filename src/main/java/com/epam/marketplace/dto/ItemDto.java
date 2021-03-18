package com.epam.marketplace.dto;

import java.util.HashSet;
import java.util.Set;

public class ItemDto {

  private Integer id;
  private String name;
  private String descript;
  private Integer userId;
  private Set<Integer> dealIds = new HashSet<>();
  private Boolean isOnSale = false;

  public ItemDto() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescript() {
    return descript;
  }

  public void setDescript(String descript) {
    this.descript = descript;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Set<Integer> getDealIds() {
    return dealIds;
  }

  public void addDealId(Integer dealId) {
    dealIds.add(dealId);
  }

  public boolean getOnSale() {
    return isOnSale;
  }

  public void setOnSale(boolean onSale) {
    isOnSale = onSale;
  }
}
