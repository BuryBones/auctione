package com.epam.marketplace.dto.mappers;

import com.epam.marketplace.entities.Role;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.dto.UserDto;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("userConverter")
@Scope("prototype")
public class UserConverter extends BidirectionalConverter<User, UserDto> {

  @Override
  public UserDto convertTo(User src, Type<UserDto> type, MappingContext mappingContext) {
    UserDto dest = new UserDto();
    dest.setId(src.getId());
    dest.setLogin(src.getLogin());
    dest.setFirstName(src.getFirstName());
    dest.setLastName(src.getLastName());
    dest.setEmail(src.getEmail());
    src.getUserRoles()
        .stream().map(Role::getRoleName)
        .forEach(dest::addRole);
    return dest;
  }

  @Override
  public User convertFrom(UserDto src, Type<User> type, MappingContext mappingContext) {
    User dest = new User();
    dest.setLogin(src.getLogin());
    dest.setFirstName(src.getFirstName());
    dest.setLastName(src.getLastName());
    dest.setPassword(src.getPassword());
    dest.setEmail(src.getEmail());
    return dest;
  }
}
