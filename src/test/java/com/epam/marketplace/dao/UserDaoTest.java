package com.epam.marketplace.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.impl.UserDaoImpl;
import com.epam.marketplace.entities.User;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
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
