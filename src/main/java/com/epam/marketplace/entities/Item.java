package com.epam.marketplace.entities;

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
@Table(name = "item")
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "descript", nullable = false)
  private String descript;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "item",cascade = CascadeType.ALL)
  private Set<Deal> deals  = new HashSet<>();

  public Item() {}

  public void addDeal(Deal deal) {
    getDeals().add(deal);
    deal.setItem(this);
  }

  public void removeDeal(Deal deal) {
    getDeals().remove(deal);
    deal.setItem(null);
  }

  @Override
  public String toString() {
    return String.format("Item ID: %d, %s, %n(%s)",
        getId(), getName(), getDescript());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Item item = (Item) o;

    if (getId() != item.getId()) {
      return false;
    }
    if (!getName().equals(item.getName())) {
      return false;
    }
    return getDescript().equals(item.getDescript());
  }

  @Override
  public int hashCode() {
    return getId();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescript() {
    return descript;
  }

  public void setDescript(String descript) {
    this.descript = descript;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<Deal> getDeals() {
    return deals;
  }

  public void setDeals(Set<Deal> deals) {
    this.deals = deals;
  }
}
