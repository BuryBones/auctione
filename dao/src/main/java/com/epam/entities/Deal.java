package main.java.com.epam.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "deal")
public class Deal {

  @Id
  @GeneratedValue
  private int id;

  @Column(name = "init_price")
  private BigDecimal init_price;

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
  private List<Bid> bids;

  public Deal() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BigDecimal getInit_price() {
    return init_price;
  }

  public void setInit_price(BigDecimal init_price) {
    this.init_price = init_price;
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

  public boolean isStatus() {
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

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }
}
