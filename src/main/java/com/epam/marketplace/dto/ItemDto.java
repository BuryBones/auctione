package com.epam.marketplace.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemDto extends AbstractDto {

  private Integer id;

  @NotNull(message = "Item name cannot be empty")
  @Size(min = 6, max = 45, message = "Item name length has to be from 6 to 45 symbols")
  private String name;

  @Size(max = 300, message = "Description length cannot be longer than 300 symbols")
  private String descript;

  @Min(1)
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
