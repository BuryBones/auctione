package main.java.com.epam.dao.impl;

import main.java.com.epam.dao.RoleDao;
import main.java.com.epam.entities.Role;

public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

  public RoleDaoImpl() {
    super(Role.class);
  }

  @Override
  public Role getRoleByName(String name) {
    return null;
  }
}
