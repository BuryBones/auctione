package main.java.com.epam.dao.impl;

import main.java.com.epam.dao.UserDao;
import main.java.com.epam.entities.User;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

  public UserDaoImpl() {
    super(User.class);
  }

  @Override
  public User getUserByLogin(String login) {
    return null;
  }
}
