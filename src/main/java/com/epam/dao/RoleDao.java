package com.epam.dao;

import java.util.Optional;
import com.epam.entities.Role;

public interface RoleDao extends CommonDao<Role> {

  Optional<Role> getRoleByName(String name);

}
