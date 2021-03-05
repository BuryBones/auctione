package com.epam.marketplace.services.mappers;

import com.epam.marketplace.entities.Role;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.services.dto.UserDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class UserCustomMapper extends CustomMapper<User, UserDto> {

  @Override
  public void mapAtoB(User src, UserDto dest, MappingContext context) {
    dest.setId(src.getId());
    dest.setLogin(src.getLogin());
    dest.setFirstName(src.getFirstName());
    dest.setLastName(src.getLastName());
    src.getUserRoles()
        .stream().map(Role::getRoleName)
        .forEach(dest::addRole);
  }

  @Override
  public void mapBtoA(UserDto src, User dest, MappingContext context) {
    dest.setLogin(src.getLogin());
    dest.setFirstName(src.getFirstName());
    dest.setLastName(src.getLastName());
    dest.setPassword(src.getPassword());
  }
}
