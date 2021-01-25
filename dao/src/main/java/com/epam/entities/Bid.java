package main.java.com.epam.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bid")
public class Bid {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "date_and_time")
  private Timestamp dateAndTime;

  @Column(name = "offer")
  private BigDecimal offer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "deal_id")
  private Deal deal;

  public Bid() {}

  @Override
  public String toString() {
    return String.format("Bid ID: %d Offer: %s",getId(),getOffer());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Bid bid = (Bid) o;

    if (getId() != bid.getId()) {
      return false;
    }
    if (!getDateAndTime().equals(bid.getDateAndTime())) {
      return false;
    }
    if (!getOffer().equals(bid.getOffer())) {
      return false;
    }
    if (!getUser().equals(bid.getUser())) {
      return false;
    }
    return getDeal().equals(bid.getDeal());
  }

  @Override
  public int hashCode() {
    int result = getId();
    result = 31 * result + getDateAndTime().hashCode();
    result = 31 * result + getOffer().hashCode();
    result = 31 * result + getUser().hashCode();
    result = 31 * result + getDeal().hashCode();
    return result;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Timestamp getDateAndTime() {
    return dateAndTime;
  }

  public void setDateAndTime(Timestamp dateAndTime) {
    this.dateAndTime = dateAndTime;
  }

  public BigDecimal getOffer() {
    return offer;
  }

  public void setOffer(BigDecimal offer) {
    this.offer = offer;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Deal getDeal() {
    return deal;
  }

  public void setDeal(Deal deal) {
    this.deal = deal;
  }
}
