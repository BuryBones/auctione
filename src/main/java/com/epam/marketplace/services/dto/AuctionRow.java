package com.epam.marketplace.services.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AuctionRow {

  private String seller;
  private String item;
  private String info;
  private LocalDateTime startDate;
  private BigDecimal startPrice;
  private BigDecimal lastBid = null;
  private LocalDateTime stopDate;

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

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
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

  public LocalDateTime getStopDate() {
    return stopDate;
  }

  public void setStopDate(LocalDateTime stopDate) {
    this.stopDate = stopDate;
  }
}
