package com.epam.marketplace.dao;

import com.epam.marketplace.entities.Role;
import java.util.Optional;

public interface RoleDao extends CommonDao<Role> {

  default Class<Role> getType() {
    return Role.class;
  }

  Optional<Role> findByName(String name);

}
