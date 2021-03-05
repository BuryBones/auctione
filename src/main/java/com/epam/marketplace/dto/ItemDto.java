package com.epam.marketplace.dto;

public class ItemDto {

  private Integer id;
  private String name;
  private String descript;
  private Integer userId;
  private Integer dealId;

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

  public Integer getDealId() {
    return dealId;
  }

  public void setDealId(Integer dealId) {
    this.dealId = dealId;
  }
}
