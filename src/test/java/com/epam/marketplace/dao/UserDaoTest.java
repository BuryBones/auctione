package com.epam.marketplace.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.marketplace.config.TestContextConfig;
import com.epam.marketplace.entities.User;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith({H2Extension.class, SpringExtension.class})
@ContextConfiguration(
    classes = TestContextConfig.class,
    loader = AnnotationConfigContextLoader.class)
public class UserDaoTest {

  @Autowired
  private UserDao userDao;

  @Test
  public void findByLoginSuccessTest() {
    // when
    Optional<User> optionalUser = userDao.findByLogin("test1");

    // then
    assertTrue(optionalUser.isPresent());
    assertNotNull(optionalUser.get());
  }

  @Test
  public void findByLoginFailTest() {
    // when
    Optional<User> optionalUser = userDao.findByLogin("no such user");

    // then
    assertFalse(optionalUser.isPresent());
  }

}
