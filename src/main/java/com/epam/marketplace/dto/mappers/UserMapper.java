package com.epam.marketplace.dto.mappers;

import com.epam.marketplace.entities.User;
import com.epam.marketplace.dto.UserDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component("userMapper")
public class UserMapper {

  private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
  private final UserCustomMapper customMapper = new UserCustomMapper();

  public UserDto getDtoFromEntity(User item) {
    mapperFactory.classMap(User.class, UserDto.class)
        .customize(customMapper)
        .register();
    MapperFacade mapper = mapperFactory.getMapperFacade();
    return mapper.map(item,UserDto.class);
  }

  public User getEntityFromDto(UserDto itemDto) {
    mapperFactory.classMap(User.class, UserDto.class)
        .customize(customMapper)
        .register();
    MapperFacade mapper = mapperFactory.getMapperFacade();
    return mapper.map(itemDto, User.class);
  }

}
