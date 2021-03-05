package com.epam.marketplace.services.dto;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DealDto {

  private Integer id;
  private String seller;
  private Integer sellerId;
  private String item;
  private Integer itemId;
  private String info;
  private Date startDate;
  private BigDecimal startPrice;
  private BigDecimal lastBid;
  private Date stopDate;
  private boolean status = true;

  public DealDto() {
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
