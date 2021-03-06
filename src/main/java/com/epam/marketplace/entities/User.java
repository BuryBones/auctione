package com.epam.marketplace.entities;

import java.util.Collection;
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
import javax.persistence.Transient;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "\"user\"")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NaturalId
  @Column(name = "login", unique = true, nullable = false)
  private String login;

  @NaturalId
  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "firstname", nullable = false)
  private String firstName;

  @Column(name = "lastname", nullable = false)
  private String lastName;

  @Column(name = "password", nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false))
  private Set<Role> userRoles = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Item> items = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Deal> deals = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
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

  @Transient
  private Collection<GrantedAuthority> authorities = new HashSet<>();

  public void addAuthority(GrantedAuthority authority) {
    authorities.add(authority);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getUsername() {
    return getLogin();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    return String.format("User ID: %d, Login: %s, Email: %s, %s %s%n",
        getId(), getLogin(), getEmail(), getLastName(), getFirstName());
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
    if (!getLogin().equals(user.getLogin())) {
      return false;
    }
    if (!getFirstName().equals(user.getFirstName())) {
      return false;
    }
    return getLastName().equals(user.getLastName());
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
