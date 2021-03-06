package com.epam.marketplace.dto;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class DealDto implements Dto {

  private Integer id;

  @Size(min = 2, max = 90, message = "Seller name has to be from 2 to 90 symbols")
  private String seller;

  private Integer sellerId;

  @Size(min = 6, max = 45, message = "Item name length has to be from 6 to 45 symbols")
  private String item;

  @NotNull(message = "Item ID cannot be empty")
  @Min(1)
  private Integer itemId;

  @Size(max = 300, message = "Description length cannot be longer than 300 symbols")
  private String info;

  private Date startDate;

  @NotNull(message = "Start price cannot be empty")
  @DecimalMin("1.00")
  private BigDecimal startPrice;

  @DecimalMin("1.00")
  private BigDecimal lastBid;

  @NotNull(message = "Stop date cannot be empty")
  @Future
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private Date stopDate;

  private boolean status = true;

  public DealDto() {

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DealDto dealDto = (DealDto) o;

    if (!getId().equals(dealDto.getId())) {
      return false;
    }
    if (!getSellerId().equals(dealDto.getSellerId())) {
      return false;
    }
    return getItemId().equals(dealDto.getItemId());
  }

  @Override
  public int hashCode() {
    int result = getId().hashCode();
    result = 31 * result + getSellerId().hashCode();
    result = 31 * result + getItemId().hashCode();
    return result;
  }

  public Integer getSellerId() {
    return sellerId;
  }

  public void setSellerId(Integer sellerId) {
    this.sellerId = sellerId;
  }

  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public BigDecimal getStartPrice() {
    return startPrice;
  }

  public void setStartPrice(BigDecimal startPrice) {
    this.startPrice = startPrice;
  }

  public BigDecimal getLastBid() {
    return lastBid;
  }

  public void setLastBid(BigDecimal lastBid) {
    this.lastBid = lastBid;
  }

  public Date getStopDate() {
    return stopDate;
  }

  public void setStopDate(Date stopDate) {
    this.stopDate = stopDate;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
