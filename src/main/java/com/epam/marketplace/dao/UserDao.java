package com.epam.marketplace.dao;

import java.util.List;
import java.util.Optional;
import com.epam.marketplace.entities.User;

public interface UserDao extends CommonDao<User> {

  default Class<User> getType() {
    return User.class;
  }

  Optional<User> findByLogin(String login);

  Optional<User> findByEmail(String email);

  List<User> findAllWithRoles();

}
