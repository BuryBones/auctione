package com.epam.dao;

import java.util.Optional;
import com.epam.entities.User;

public interface UserDao extends CommonDao<User> {

  default Class<User> getType() {
    return User.class;
  }

  Optional<User> findByLogin(String login);

}
