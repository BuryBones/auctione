package main.java.com.epam.entities;

import java.util.List;
import java.util.Set;
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
  @Column(name = "login")
  private String login;

  @Column(name = "firstname")
  private String firstName;

  @Column(name = "lastname")
  private String lastName;

  @Column(name = "password")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> userRoles;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private Set<Item> items;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private Set<Deal> deals;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private Set<Bid> bids;

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
    if (!getLogin().equals(user.getLogin())) {
      return false;
    }
    if (!getFirstName().equals(user.getFirstName())) {
      return false;
    }
    if (!getLastName().equals(user.getLastName())) {
      return false;
    }
    if (!getPassword().equals(user.getPassword())) {
      return false;
    }
    if (getUserRoles() != null ? !getUserRoles().equals(user.getUserRoles())
        : user.getUserRoles() != null) {
      return false;
    }
    if (getItems() != null ? !getItems().equals(user.getItems()) : user.getItems() != null) {
      return false;
    }
    if (getDeals() != null ? !getDeals().equals(user.getDeals()) : user.getDeals() != null) {
      return false;
    }
    return getBids() != null ? getBids().equals(user.getBids()) : user.getBids() == null;
  }

  @Override
  public int hashCode() {
    int result = getId();
    result = 31 * result + getLogin().hashCode();
    result = 31 * result + getFirstName().hashCode();
    result = 31 * result + getLastName().hashCode();
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
