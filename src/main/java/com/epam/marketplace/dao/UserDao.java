package com.epam.marketplace.dao;

import com.epam.marketplace.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserDao extends CommonDao<User> {

  default Class<User> getType() {
    return User.class;
  }

  Optional<User> findByLogin(String login);

  Optional<User> findByLoginWithRoles(String login);

  Optional<User> findByEmail(String email);

  List<User> findAllWithRoles();

}
