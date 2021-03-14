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
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

  private final UserDao userDao;
  private final RoleDao roleDao;
  private final CommonMapper mapper;

  @Autowired
  public UserService(UserDao userDao, RoleDao roleDao, CommonMapper mapper) {
    this.userDao = userDao;
    this.roleDao = roleDao;
    this.mapper = mapper;
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
    try {
      setDefaultRole(newUser);
      userDao.save(newUser);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public boolean checkCredentials(String login, String password) {
    Optional<User> optionalUser = userDao.findByLogin(login);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      return user.getLogin().equals(login) && user.getPassword().equals(password);
    } else {
      return false;
    }
  }

  public boolean checkIfUserExistsByLogin(String login) {
    return userDao.findByLogin(login).isPresent();
  }

  private void setDefaultRole(User user) {
    HashSet<Role> roles = new HashSet<>();
    roles.add(roleDao.findById(2).get());
    user.setUserRoles(roles);
  }

}
