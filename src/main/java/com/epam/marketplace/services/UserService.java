package com.epam.marketplace.services;

import com.epam.marketplace.dao.RoleDao;
import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Role;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.dto.UserDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

  private final UserDao userDao;
  private final RoleDao roleDao;
  private final CommonMapper mapper;
  // TODO: отедельный бин Бкрипт,
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserDao userDao, RoleDao roleDao, CommonMapper mapper, PasswordEncoder passwordEncoder) {
    this.userDao = userDao;
    this.roleDao = roleDao;
    this.mapper = mapper;
    this.passwordEncoder = passwordEncoder;
  }

  public List<UserDto> getUsers() {
    List<User> users = userDao.findAllWithRoles();
    ArrayList<UserDto> result = new ArrayList<>(users.size());
    for (User u: users) {
      result.add(mapper.getDtoFromEntity(u));
    }
    return result;
  }

  public boolean createUser(UserDto newBorn) {
    User newUser = mapper.getEntityFromDto(newBorn);
    // TODO: find out how to add encoder ID (not like this)
    newUser.setPassword("{bcrypt}" + passwordEncoder.encode(newUser.getPassword()));
    try {
      setDefaultRole(newUser);
      userDao.save(newUser);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public boolean checkIfUserExistsByLogin(String login) {
    return userDao.findByLogin(login).isPresent();
  }

  public boolean checkIfEmailAlreadyRegistered(String email) {
    return userDao.findByEmail(email).isPresent();
  }

  public String getCurrentUserName() {
    Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (obj instanceof User) {
      User user = (User) obj;
      return user.getLastName() + " " + user.getFirstName();
    } else {
      return "Guest";
    }
  }

  public int getCurrentUserId() {
    Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (obj instanceof User) {
      User user = (User) obj;
      return user.getId();
    } else {
      return 0;
    }
  }

  private void setDefaultRole(User user) {
    HashSet<Role> roles = new HashSet<>();
    roles.add(roleDao.findById(2).get());
    user.setUserRoles(roles);
  }

}
