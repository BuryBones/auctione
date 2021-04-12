package com.epam.marketplace.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.impl.ItemDaoImpl;
import com.epam.marketplace.entities.Item;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
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
