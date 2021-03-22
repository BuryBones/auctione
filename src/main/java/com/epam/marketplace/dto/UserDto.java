package com.epam.marketplace.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto extends AbstractDto {

  private Integer id;

  @NotNull(message = "Login cannot be empty")
  @Size(min = 6, max = 45, message = "Login length has to be from 6 to 45 symbols")
  @Pattern(regexp = "(^[a-zA-Z0-9]+[a-zA-Z0-9._-]+$)",
      message = "Login contains unacceptable symbols")
  private String login;

  @NotNull(message = "Password cannot be empty")
  @Size(min = 8, max = 45, message = "Password length has to be from 8 to 45 symbols")
  @Pattern(regexp = "(^[a-zA-Z0-9!#$*+_^.,-]+$)",
      message = "Password contains unacceptable symbols")
  private String password;

  @NotNull(message = "First name cannot be empty")
  @Size(min = 1, max = 45, message = "First name length has to be from 1 to 45 symbols")
  @Pattern(regexp = "(^[a-zA-Z]+[- ]*[a-zA-Z]+$)",
      message = "First name contains unacceptable symbols")
  private String firstName;

  @NotNull(message = "Last name cannot be empty")
  @Size(min = 1, max = 45, message = "Last name length has to be from 1 to 45 symbols")
  @Pattern(regexp = "(^[a-zA-Z]+[- ]*[a-zA-Z]+$)",
      message = "Last name contains unacceptable symbols")
  private String lastName;

  @NotNull(message = "Email Name cannot be empty")
  @Size(min = 3, max = 45, message = "Email length has to be from 3 to 45 symbols")
  @Pattern(regexp = "(^[a-zA-Z0-9]+[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$)",
      message = "Incorrect email format")
  private String email;

  private Set<String> roles = new HashSet<>();

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void addRole(String role) {
    this.roles.add(role);
  }
}
