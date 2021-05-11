package com.epam.marketplace.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemDto implements Dto {

  private Integer id;

  @NotNull(message = "Item name cannot be empty")
  @Size(min = 6, max = 45, message = "Item name length has to be from 6 to 45 symbols")
  private String name;

  @Size(max = 300, message = "Description length cannot be longer than 300 symbols")
  private String description;

  private Integer userId;
  private Set<Integer> dealIds = new HashSet<>();
  private Boolean isOnSale = false;

  public ItemDto() {

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ItemDto itemDto = (ItemDto) o;

    if (!getId().equals(itemDto.getId())) {
      return false;
    }
    return getName().equals(itemDto.getName());
  }

  @Override
  public int hashCode() {
    return getId().hashCode();
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
