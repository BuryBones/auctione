package com.epam.entities;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "\"user\"")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NaturalId
  @Column(name = "login", unique = true, nullable = false)
  private String login;

  @Column(name = "firstname", nullable = false)
  private String firstName;

  @Column(name = "lastname", nullable = false)
  private String lastName;

  @Column(name = "password", nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false))
  private Set<Role> userRoles = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.ALL)
  private Set<Item> items = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.ALL)
  private Set<Deal> deals = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.ALL)
  private Set<Bid> bids = new HashSet<>();

  public User() {
  }

  public void addRole(Role role) {
    getUserRoles().add(role);
    role.getUsers().add(this);
  }

  public void removeRole(Role role) {
    getUserRoles().remove(role);
    role.getUsers().remove(this);
  }

  public void addItem(Item item) {
    getItems().add(item);
    item.setUser(this);
  }

  public void removeItem(Item item) {
    getItems().remove(item);
    item.setUser(null);
  }

  public void addDeal(Deal deal) {
    getDeals().add(deal);
    deal.setUser(this);
  }

  public void removeDeal(Deal deal) {
    getDeals().remove(deal);
    deal.setUser(null);
  }

  public void addBid(Bid bid) {
    getBids().add(bid);
    bid.setUser(this);
  }

  public void removeBid(Bid bid) {
    getBids().remove(bid);
    bid.setUser(null);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    return String.format("User ID: %d, %s %s%n", getId(), getLastName(), getFirstName());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User user = (User) o;

    if (getId() != user.getId()) {
      return false;
    }
    return getLogin().equals(user.getLogin());
  }

  @Override
  public int hashCode() {
    int result = getId();
    result = 31 * result + getLogin().hashCode();
    return result;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(Set<Role> userRoles) {
    this.userRoles = userRoles;
  }

  public Set<Item> getItems() {
    return items;
  }

  public void setItems(Set<Item> items) {
    this.items = items;
  }

  public Set<Deal> getDeals() {
    return deals;
  }

  public void setDeals(Set<Deal> deals) {
    this.deals = deals;
  }

  public Set<Bid> getBids() {
    return bids;
  }

  public void setBids(Set<Bid> bids) {
    this.bids = bids;
  }
}
