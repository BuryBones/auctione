package com.epam.marketplace.services.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

  private Integer id;
  private String login = "";
  private String password = "";
  private String firstName;
  private String lastName;
  private List<String> roles = new ArrayList<>();

  public UserDto() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public List<String> getRoles() {
    return roles;
  }

  public void addRole(String role) {
    this.roles.add(role);
  }
}
