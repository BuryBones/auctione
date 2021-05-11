package com.epam.marketplace.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.marketplace.config.TestContextConfig;
import com.epam.marketplace.entities.Item;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith({H2Extension.class, SpringExtension.class})
@ContextConfiguration(
    classes = TestContextConfig.class,
    loader = AnnotationConfigContextLoader.class)
public class ItemDaoTest {

  @Autowired
  private ItemDao itemDao;

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
