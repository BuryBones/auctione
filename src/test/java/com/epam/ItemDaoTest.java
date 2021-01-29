package com.epam;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.dao.ItemDao;
import com.epam.dao.impl.ItemDaoImpl;
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
  public void findByName() {

  }

}
