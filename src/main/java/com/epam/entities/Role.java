package com.epam.entities;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "role_name")
  private String roleName;

  @ManyToMany(mappedBy = "userRoles",cascade = CascadeType.ALL)
  private Set<User> users;

  public Role() {}

  public void addUser(User user) {
    user.addRole(this);
  }

  public void removeUser(User user) {
    user.removeRole(this);
  }

  @Override
  public String toString() {
    return String.format("Role ID: %d, %s",getId(),getRoleName());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Role role = (Role) o;

    if (getId() != role.getId()) {
      return false;
    }
    return getRoleName().equals(role.getRoleName());
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

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }
}
