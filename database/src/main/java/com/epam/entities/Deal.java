package main.java.com.epam.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
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

  @Column(name = "init_price")
  private BigDecimal initPrice;

  @Column(name = "open_time")
  private Timestamp openTime;

  @Column(name = "close_time")
  private Timestamp closeTime;

  @Column(name = "status")
  private boolean status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "deal")
  private Set<Bid> bids;

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
    if (!getCloseTime().equals(deal.getCloseTime())) {
      return false;
    }
    if (!getUser().equals(deal.getUser())) {
      return false;
    }
    if (!getItem().equals(deal.getItem())) {
      return false;
    }
    return getBids() != null ? getBids().equals(deal.getBids()) : deal.getBids() == null;
  }

  @Override
  public int hashCode() {
    int result = getId();
    result = 31 * result + getInitPrice().hashCode();
    result = 31 * result + getOpenTime().hashCode();
    result = 31 * result + getCloseTime().hashCode();
    result = 31 * result + (getStatus() ? 1 : 0);
    result = 31 * result + getUser().hashCode();
    result = 31 * result + getItem().hashCode();
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

  public Timestamp getOpenTime() {
    return openTime;
  }

  public void setOpenTime(Timestamp openTime) {
    this.openTime = openTime;
  }

  public Timestamp getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(Timestamp closeTime) {
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
