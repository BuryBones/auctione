package main.java.com.epam.dao;

import main.java.com.epam.entities.User;

public interface UserDao extends CommonDao<User> {

  User getUserByLogin(String login);

}
