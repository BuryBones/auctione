package main.java.com.epam.dao;

import java.util.Optional;
import main.java.com.epam.entities.Role;

public interface RoleDao extends CommonDao<Role> {

  Optional<Role> getRoleByName(String name);

}
