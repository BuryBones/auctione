package com.epam.marketplace.dao;

import java.util.Optional;
import com.epam.marketplace.entities.Role;

public interface RoleDao extends CommonDao<Role> {

  default Class<Role> getType() {
    return Role.class;
  }

  Optional<Role> findByName(String name);

}
