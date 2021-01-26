package main.java.com.epam.dao;

import java.util.Optional;
import main.java.com.epam.entities.User;

public interface UserDao extends CommonDao<User> {

  Optional<User> getUserByLogin(String login);

}
