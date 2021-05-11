package com.epam.marketplace.dto;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BidDto implements Dto {

  private Integer id;

  private Date dateAndTime;

  @NotNull(message = "Offer cannot be empty")
  @DecimalMin("1.00")
  private BigDecimal offer;

  private Integer userId;

  @NotNull(message = "Deal ID cannot be empty")
  @Min(1)
  private Integer dealId;

  public BidDto() {

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BidDto bidDto = (BidDto) o;

    if (!getId().equals(bidDto.getId())) {
      return false;
    }
    if (!getDateAndTime().equals(bidDto.getDateAndTime())) {
      return false;
    }
    if (!getOffer().equals(bidDto.getOffer())) {
      return false;
    }
    if (!getUserId().equals(bidDto.getUserId())) {
      return false;
    }
    return getDealId().equals(bidDto.getDealId());
  }

  @Override
  public int hashCode() {
    int result = getId().hashCode();
    result = 31 * result + getDateAndTime().hashCode();
    result = 31 * result + getOffer().hashCode();
    result = 31 * result + getUserId().hashCode();
    result = 31 * result + getDealId().hashCode();
    return result;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getDateAndTime() {
    return dateAndTime;
  }

  public void setDateAndTime(Date dateAndTime) {
    this.dateAndTime = dateAndTime;
  }

  public BigDecimal getOffer() {
    return offer;
  }

  public void setOffer(BigDecimal offer) {
    this.offer = offer;
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
