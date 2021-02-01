package com.epam.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.HibernateUtil;
import com.epam.dao.UserDao;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entities.User;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
