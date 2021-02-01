package com.epam.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.epam.HibernateUtil;
import com.epam.dao.impl.ItemDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entities.Item;
import com.epam.entities.Item_;
import com.epam.entities.User;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CommonDaoItemTest {

  private static UserDao userDao;
  private static ItemDao itemDao;

  @BeforeAll
  private static void setup() {
    HibernateUtil.init();
    userDao = new UserDaoImpl();
    itemDao = new ItemDaoImpl();
  }

  @Test
  public void findByIdItemTest() {
    // given
    Item expected = new Item();
    expected.setId(4);
    expected.setName("Test Item 4");

    // when
    Optional<Item> optionalItem = itemDao.findById(4);

    // then
    assertTrue(optionalItem.isPresent());
    assertNotNull(optionalItem.get());
    assertEquals(optionalItem.get(),expected);
  }

  @Test
  public void findAllItemTest() {
    // when
    List<Item> allItems = itemDao.findAll();

    // then
    assertEquals(allItems.size(), 8);
    allItems.forEach(Assertions::assertNotNull);
  }

  @Test
  public void saveItemTest() {
    // given
    Item expected = new Item();
    expected.setName("test_item1");
    expected.setDescript("test");

    Optional<User> optionalUser = userDao.findById(1);
    if (optionalUser.isPresent()) {
      expected.setUser(optionalUser.get());
    } else {
      fail();
    }

    // when
    itemDao.save(expected);
    itemDao.refresh(expected);
    Item actual = null;
    Optional<Item> optionalItem = itemDao.findByName("test_item1");
    if (optionalItem.isPresent()) {
      actual = optionalItem.get();
    } else {
      fail();
    }

    // then
    assertEquals(expected,actual);

    // cleanup
    itemDao.delete(expected);
  }

  @Test
  public void updateItemTest() {
    // given
    Optional<Item> optionalItem = itemDao.findByName("Test Item 1");
    Item expected = null;
    if (optionalItem.isPresent()) {
      expected = optionalItem.get();
    } else {
      fail();
    }

    // when
    expected.setName("Test Item 1 CHANGED");
    expected.setDescript("DESCRIPTION CHANGED");
    itemDao.update(expected);
    Optional<Item> actual = itemDao.findByName("Test Item 1 CHANGED");

    // then
    assertTrue(actual.isPresent());
    assertNotNull(actual.get());
    assertEquals(expected,actual.get());

    // cleanup
    expected.setName("Test Item 1");
    expected.setDescript("description");
    itemDao.update(expected);
  }

  @Test
  public void deleteItemTest() {
    // given
    Item testItem = new Item();
    testItem.setName("TO DELETE");
    testItem.setDescript("TO DELETE");

    Optional<User> optionalUser = userDao.findById(1);
    if (optionalUser.isPresent()) {
      testItem.setUser(optionalUser.get());
    } else {
      fail();
    }

    itemDao.save(testItem);
    assertTrue(itemDao.findByName("TO DELETE").isPresent());

    // when
    itemDao.delete(testItem);

    // then
    assertTrue(itemDao.findByName("TO DELETE").isEmpty());
  }

  @Test
  public void findByIdWithAttributesItemTest() {
    // when
    Optional<Item> optionalItem = itemDao.findByIdWithAttributes(
        2, Item_.user,Item_.deals);

    // then
    assertTrue(optionalItem.isPresent());
    Item item = optionalItem.get();
    assertNotNull(item);
    assertNotNull(item.getUser());
    assertFalse(item.getDeals().isEmpty());
  }
}
