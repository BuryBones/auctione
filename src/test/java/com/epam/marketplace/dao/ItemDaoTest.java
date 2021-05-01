package com.epam.marketplace.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.impl.ItemDaoImpl;
import com.epam.marketplace.entities.Item;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(H2Extension.class)
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
