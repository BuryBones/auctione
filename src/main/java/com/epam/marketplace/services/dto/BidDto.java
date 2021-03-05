package com.epam.marketplace.services.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BidDto {

  private Integer id;
  private Date dateAndTime;
  private BigDecimal offer;
  private Integer userId;
  private Integer dealId;

  public BidDto(){
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
