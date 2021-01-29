package com.epam;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.dao.BidDao;
import com.epam.dao.impl.BidDaoImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BidDaoTest {

  private static BidDao bidDao;

  @BeforeAll
  private static void setup() {
    HibernateUtil.init();
    bidDao = new BidDaoImpl();
  }

  @Test
  public void findFromDateTest() {


  }

}
