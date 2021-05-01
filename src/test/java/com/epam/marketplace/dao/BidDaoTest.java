package com.epam.marketplace.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.impl.BidDaoImpl;
import com.epam.marketplace.entities.Bid;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(H2Extension.class)
public class BidDaoTest {

  private static BidDao bidDao;

  @BeforeAll
  private static void setup() {
    HibernateUtil.init();
    bidDao = new BidDaoImpl();
  }

  @Test
  public void findFromDateTest() {
    // when
    LocalDateTime date = LocalDateTime.of(2021, 1, 3, 0, 0);
    List<Bid> listOfExpected = bidDao.findFromDate(date);
    List<Bid> listOfAll = bidDao.findAll();

    // then
    assertTrue(listOfAll.stream()                                 // check if there any deal before
        .anyMatch(deal -> deal.getDateAndTime().isBefore(date))); // the date among all deals
    listOfExpected.forEach(Assertions::assertNotNull);
    listOfExpected.forEach(deal -> assertTrue(deal.getDateAndTime().isAfter(date)));
  }

}
