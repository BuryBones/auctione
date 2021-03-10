package com.epam.marketplace.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.impl.RoleDaoImpl;
import com.epam.marketplace.dao.impl.UserDaoImpl;
import com.epam.marketplace.entities.Role;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.entities.User_;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CommonDaoUserTest {

  private static UserDao userDao;
  private static RoleDao roleDao;

  @BeforeAll
  private static void setup() {
    HibernateUtil.init();
    userDao = new UserDaoImpl();
    roleDao = new RoleDaoImpl();
  }

  @Test
  public void findByIdUserTest() {
    // given
    User expected = new User();
    expected.setId(1);
    expected.setLogin("test1");
    expected.setFirstName("TEST 1 FIRST");
    expected.setLastName("TEST 1 LAST");

    // when
    Optional<User> optionalUser = userDao.findById(1);

    // then
    assertTrue(optionalUser.isPresent());
    assertNotNull(optionalUser.get());
    assertEquals(optionalUser.get(),expected);
  }

  @Test
  public void findAllUserTest() {
    // when
    List<User> allUsers = userDao.findAll();

    // then
    assertEquals(allUsers.size(), 8);
    allUsers.forEach(Assertions::assertNotNull);
  }

  @Test
  public void saveUserTest() {
    // given
    User expected = new User();
    expected.setLogin("test_login1");
    expected.setPassword("test_password");
    expected.setFirstName("Test");
    expected.setLastName("Testing");

    Optional<Role> optionalRole = roleDao.findById(1);
    if (optionalRole.isPresent()) {
      expected.getUserRoles().add(optionalRole.get());
    } else {
      fail();
    }

    // when
    userDao.save(expected);
    userDao.refresh(expected);
    User actual = null;
    Optional<User> optionalUser = userDao.findByLogin("test_login1");
    if (optionalUser.isPresent()) {
      actual = optionalUser.get();
    } else {
      fail();
    }

    // then
    assertEquals(expected,actual);

    // cleanup
    userDao.delete(expected);
  }

  @Test
  public void updateUserTest() {
    // given
    Optional<User> optionalUser = userDao.findById(1);
    User expected = null;
    if (optionalUser.isPresent()) {
      expected = optionalUser.get();
    } else {
      fail();
    }

    // when
    expected.setFirstName("CHANGED");
    userDao.update(expected);
    Optional<User> actual = userDao.findById(1);

    // then
    assertTrue(actual.isPresent());
    assertNotNull(actual.get());
    assertEquals(expected,actual.get());

    // cleanup
    expected.setFirstName("TEST 1 FIRST");
    userDao.update(expected);
  }

  @Test
  public void deleteUserTest() {
    // given
    User testUser = new User();
    testUser.setLogin("TO DELETE");
    testUser.setPassword("test_password");
    testUser.setFirstName("Test");
    testUser.setLastName("Testing");

    Optional<Role> optionalRole = roleDao.findById(1);
    if (optionalRole.isPresent()) {
      testUser.getUserRoles().add(optionalRole.get());
    } else {
      fail();
    }

    userDao.save(testUser);
    assertTrue(userDao.findByLogin("TO DELETE").isPresent());

    // when
    userDao.delete(testUser);

    // then
    assertTrue(userDao.findByLogin("TO DELETE").isEmpty());
  }

  @Test
  public void findByIdWithAttributesUserTest() {
    // when
    Optional<User> optionalUser = userDao.findByIdWithAttributes(
        2, User_.items, User_.deals, User_.userRoles);

    // then
    assertTrue(optionalUser.isPresent());
    User user = optionalUser.get();
    assertNotNull(user);
    assertFalse(user.getUserRoles().isEmpty());
    assertFalse(user.getDeals().isEmpty());
    assertFalse(user.getItems().isEmpty());
  }
}
