package main.java.com.epam.dao;

import main.java.com.epam.entities.Role;

public interface RoleDao extends CommonDao<Role> {

  Role getRoleByName(String name);

}
