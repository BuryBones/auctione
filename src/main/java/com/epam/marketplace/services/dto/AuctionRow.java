package com.epam.marketplace.services.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AuctionRow {

  private String seller;
  private String item;
  private String info;
  private String startDate;
  private BigDecimal startPrice;
  private BigDecimal lastBid = null;
  private String stopDate;

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

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
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

  public String getStopDate() {
    return stopDate;
  }

  public void setStopDate(String stopDate) {
    this.stopDate = stopDate;
  }
}
