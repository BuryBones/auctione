package com.epam.marketplace.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.impl.UserDaoImpl;
import com.epam.marketplace.entities.User;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(H2Extension.class)
public class UserDaoTest {

  private static UserDao userDao;

  @BeforeAll
  private static void setup() {
    HibernateUtil.init();
    userDao = new UserDaoImpl();
  }

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
