package main.java.com.epam.entities;

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
@Table(name = "item")
public class Item {

  @Id
  @GeneratedValue
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "descript")
  private String descript;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
  private List<Deal> deals;

  public Item() {
  }

  @Override
  public String toString() {
    return String.format("Item ID: %d, %s, %n(%s)%nOwner: %s %s",
        getId(), getName(), getDescript(), getUser().getLastName(), getUser().getFirstName());
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

  public List<Deal> getDeals() {
    return deals;
  }

  public void setDeals(List<Deal> deals) {
    this.deals = deals;
  }
}
