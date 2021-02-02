package com.epam.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.HibernateUtil;
import com.epam.dao.impl.ItemDaoImpl;
import com.epam.entities.Item;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ItemDaoTest {

  private static ItemDao itemDao;

  @BeforeAll
  public static void setup() {
    HibernateUtil.init();
    itemDao = new ItemDaoImpl();
  }

  @Test
  public void findByNameSuccessTest() {
    // when
    Optional<Item> optionalItem = itemDao.findByName("Test Item 5");

    // then
    assertTrue(optionalItem.isPresent());
    assertNotNull(optionalItem.get());
  }

  @Test
  public void findByNameFailTest() {
    // when
    Optional<Item> optionalItem = itemDao.findByName("no such item");

    // then
    assertFalse(optionalItem.isPresent());
  }

}
