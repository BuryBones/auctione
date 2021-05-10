package com.epam.marketplace.dto.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.entities.User;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserConverterTest {

  private BoundMapperFacade<User, UserDto> userConverter;
  private User user;
  private UserDto userDto;

  @BeforeEach
  private void setup() {
    BidirectionalConverter<User, UserDto> converter = new UserConverter();
    final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
    converterFactory.registerConverter(converter);
    userConverter = mapperFactory.getMapperFacade(User.class, UserDto.class);

    user = new User();
    user.setLogin("testLogin");
    user.setFirstName("testFirstName");
    user.setLastName("testLastName");
    user.setEmail("test@email.com");

    userDto = new UserDto();
    userDto.setId(12);
    userDto.setLogin("testLogin");
    userDto.setFirstName("testFirstName");
    userDto.setLastName("testLastName");
    userDto.setEmail("test@email.com");
  }

  @Test
  @DisplayName("User to UserDTO test")
  public void convertUserToDtoTest() {
    // when
    UserDto actual = userConverter.map(user);

    // then
    assertEquals(userDto, actual);
  }

  @Test
  @DisplayName("UserDTO to User test")
  public void convertDtoToUserTest() {
    // when
    User actual = userConverter.mapReverse(userDto);

    // then
    assertEquals(user, actual);
  }
}
