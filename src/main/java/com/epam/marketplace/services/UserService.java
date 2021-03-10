package com.epam.marketplace.services;

import com.epam.marketplace.dao.RoleDao;
import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.entities.Role;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.dto.mappers.UserMapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

  @Autowired
  private UserDao userDao;
  @Autowired
  private RoleDao roleDao;
  @Autowired
  private UserMapper userMapper;

  public List<UserDto> getUsers() {
    List<User> users = userDao.findAllWithRoles();
    ArrayList<UserDto> result = new ArrayList<>(users.size());
    for (User u: users) {
      result.add(userMapper.getDtoFromEntity(u));
    }
    return result;
  }

  public boolean createUser(UserDto newBorn) {
    User newUser = userMapper.getEntityFromDto(newBorn);
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

  private void setDefaultRole(User user) {
    HashSet<Role> roles = new HashSet<>();
    roles.add(roleDao.findById(2).get());
    user.setUserRoles(roles);
  }

}
