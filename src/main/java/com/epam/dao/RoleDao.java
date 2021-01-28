package com.epam.dao;

import java.util.Optional;
import com.epam.entities.Role;

public interface RoleDao extends CommonDao<Role> {

  default Class<Role> getType() {
    return Role.class;
  }

  Optional<Role> findByName(String name);

}
