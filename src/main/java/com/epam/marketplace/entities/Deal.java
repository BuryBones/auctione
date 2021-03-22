package com.epam.marketplace.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "deal")
public class Deal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "init_price", nullable = false)
  private BigDecimal initPrice;

  @Column(name = "open_time", nullable = false)
  private LocalDateTime openTime;

  @Column(name = "close_time", nullable = false)
  private LocalDateTime closeTime;

  @Column(name = "status", nullable = false)
  private boolean status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "deal",cascade = CascadeType.ALL)
  private Set<Bid> bids = new HashSet<>();

  public Deal() {}

  public void addBid(Bid bid) {
    getBids().add(bid);
    bid.setDeal(this);
  }

  public void removeBid(Bid bid) {
    getBids().remove(bid);
    bid.setDeal(null);
  }

  @Override
  public String toString() {
    return String.format("Deal ID: %d, from %s till %s%n start price %s, Open: %b",
        getId(),getOpenTime(),getCloseTime(), getInitPrice(),getStatus());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Deal deal = (Deal) o;

    if (getId() != deal.getId()) {
      return false;
    }
    if (getStatus() != deal.getStatus()) {
      return false;
    }
    if (!getInitPrice().equals(deal.getInitPrice())) {
      return false;
    }
    if (!getOpenTime().equals(deal.getOpenTime())) {
      return false;
    }
    return getCloseTime().equals(deal.getCloseTime());
  }

  @Override
  public int hashCode() {
    int result = getId();
    result = 31 * result + getInitPrice().hashCode();
    result = 31 * result + getOpenTime().hashCode();
    return result;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BigDecimal getInitPrice() {
    return initPrice;
  }

  public void setInitPrice(BigDecimal initPrice) {
    this.initPrice = initPrice;
  }

  public LocalDateTime getOpenTime() {
    return openTime;
  }

  public void setOpenTime(LocalDateTime openTime) {
    this.openTime = openTime;
  }

  public LocalDateTime getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(LocalDateTime closeTime) {
    this.closeTime = closeTime;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public Set<Bid> getBids() {
    return bids;
  }

  public void setBids(Set<Bid> bids) {
    this.bids = bids;
  }
}
