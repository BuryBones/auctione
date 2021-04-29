package com.epam.marketplace.dto.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.entities.User;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

    // move out of setup?
    userDto = new UserDto();
    userDto.setId(12);
    userDto.setLogin("testLogin");
    userDto.setFirstName("testFirstName");
    userDto.setLastName("testLastName");
    userDto.setEmail("test@email.com");
  }

  @Test
  public void convertToTest() {
    // given
    user = mock(User.class);
    when(user.getId()).thenReturn(12);
    when(user.getFirstName()).thenReturn("testFirstName");
    when(user.getLastName()).thenReturn(("testLastName"));
    when(user.getLogin()).thenReturn("testLogin");
    when(user.getEmail()).thenReturn("test@email.com");

    // when
    UserDto actual = userConverter.map(user);

    // then
    assertEquals(userDto, actual);
  }

  @Disabled
  @Test
  public void convertFromTest() {
    // when
    User actual = userConverter.mapReverse(userDto);

    // then
    assertEquals(user, actual);


  }
}
