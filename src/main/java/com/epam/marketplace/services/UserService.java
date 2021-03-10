package com.epam.marketplace.services;

import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.services.dto.UserDto;
import com.epam.marketplace.services.mappers.UserMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

  @Autowired
  private UserDao userDao;
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
      userDao.save(newUser);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

}
