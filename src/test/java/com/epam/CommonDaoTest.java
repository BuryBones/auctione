package com.epam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.epam.dao.BidDao;
import com.epam.dao.DealDao;
import com.epam.dao.ItemDao;
import com.epam.dao.RoleDao;
import com.epam.dao.UserDao;
import com.epam.dao.impl.BidDaoImpl;
import com.epam.dao.impl.DealDaoImpl;
import com.epam.dao.impl.ItemDaoImpl;
import com.epam.dao.impl.RoleDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entities.Item;
import com.epam.entities.Role;
import com.epam.entities.User;
import com.epam.entities.User_;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CommonDaoTest {

  private static UserDao userDao;
  private static ItemDao itemDao;
  private static RoleDao roleDao;
  private static DealDao dealDao;
  private static BidDao bidDao;

  @BeforeAll
  private static void setup() {
    HibernateUtil.init();
    userDao = new UserDaoImpl();
    itemDao = new ItemDaoImpl();
    roleDao = new RoleDaoImpl();
    dealDao = new DealDaoImpl();
    bidDao = new BidDaoImpl();
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
  public void findAllTest() {
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
    User user = null;
    Optional<User> optionalUser = userDao.findByIdWithAttributes(8, User_.items);
    if (optionalUser.isPresent()) {
      user = optionalUser.get();
    } else {
      fail();
    }

    Item item = new Item();
    item.setUser(user);
    item.setName("Test Item");
    item.setDescript("Testing");

    // when
    user.addItem(item);
    itemDao.save(item);
    itemDao.refresh(item);
    userDao.update(user);

    // then
    optionalUser = userDao.findByIdWithAttributes(8, User_.items);
    if (optionalUser.isPresent()) {
      user = optionalUser.get();
    } else {
      fail();
    }
    assertTrue(user.getItems().contains(item));

    // cleanup
    itemDao.delete(item);
  }

  @Test
  public void deleteUserTest() {
    // given
    User testUser = new User();
    testUser.setLogin("test_login2");
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
    assertTrue(userDao.findByLogin("test_login2").isPresent());

    // when
    userDao.delete(testUser);

    // then
    assertTrue(userDao.findByLogin("test_login2").isEmpty());
  }

  @Test
  public void findByIdWithAttributesUserTest() {
    // when
    Optional<User> optionalUser = userDao.findByIdWithAttributes(
        7, User_.items, User_.deals, User_.userRoles);

    // then
    assertTrue(optionalUser.isPresent());
    User user = optionalUser.get();
    assertNotNull(user);
    assertFalse(user.getUserRoles().isEmpty());
    assertFalse(user.getDeals().isEmpty());
    assertFalse(user.getItems().isEmpty());
  }

}
