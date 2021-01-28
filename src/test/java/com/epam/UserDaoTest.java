package com.epam;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.dao.UserDao;
import com.epam.dao.impl.RoleDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entities.Role;
import com.epam.entities.User;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserDaoTest {

  private static UserDao userDao;

  @BeforeAll
  public static void setup() {
    userDao = new UserDaoImpl();
  }

  @Test
  public void findByIdTest() {
    // given
    User expected = new User();
    expected.setId(1);
    expected.setLogin("ivan1990");

    // when
    Optional<User> optionalUser = userDao.findById(1);

    // then
    assertTrue(optionalUser.isPresent());
    assertNotNull(optionalUser.get());
    assertEquals(optionalUser.get(),expected);
  }

  @Test
  public void saveUserTest() {
    // given
    User expected = new User();
    expected.setLogin("test_login1");
    expected.setPassword("test_password");
    expected.setFirstName("Test");
    expected.setLastName("Testing");

    Role role = new RoleDaoImpl().findById(1).get();
    expected.getUserRoles().add(role);

    // when
    userDao.save(expected);
//    userDao.refresh(expected);
    User actual = userDao.findByLogin("test_login1").get();

    // then
    assertEquals(expected,actual);

    // cleanup
    userDao.delete(expected);

  }

  @Test
  public void deleteUserTest() {
    // given
    User testUser = new User();
    testUser.setLogin("test_login2");
    testUser.setPassword("test_password");
    testUser.setFirstName("Test");
    testUser.setLastName("Testing");

    Role role = new RoleDaoImpl().findById(1).get();
    testUser.getUserRoles().add(role);

    userDao.save(testUser);
    assertTrue(userDao.findByLogin("test_login2").isPresent());

    // when
    userDao.delete(testUser);

    // then
    assertTrue(userDao.findByLogin("test_login2").isEmpty());
  }

  @Test
  public void updateUserTest() {

  }

  @Test
  public void findAllTest() {

  }
}
