package com.epam.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.HibernateUtil;
import com.epam.dao.impl.DealDaoImpl;
import com.epam.entities.Deal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DealDaoTest {

  private static DealDao dealDao;

  @BeforeAll
  private static void setup() {
    HibernateUtil.init();
    dealDao = new DealDaoImpl();
  }

  @Test
  public void findByStatusOpenTest() {
    // when
    List<Deal> openDeals = dealDao.findByStatus(true);

    // then
    openDeals.forEach(deal -> assertTrue(deal.getStatus()));
  }

  @Test
  public void findByStatusClosedTest() {
    // when
    List<Deal> closedDeals = dealDao.findByStatus(false);

    // then
    closedDeals.forEach(deal -> assertFalse(deal.getStatus()));
  }

  @Test
  public void findFromDateTest() {
    // when
    LocalDateTime date = LocalDateTime.of(2021,1,2,0,0);
    List<Deal> listOfExpected = dealDao.findFromDate(date);
    List<Deal> listOfAll = dealDao.findAll();

    // then
    assertTrue(listOfAll.stream()                             // check if there any deal before
        .anyMatch(deal -> deal.getOpenTime().isBefore(date))); // the date among all deals
    listOfExpected.forEach(Assertions::assertNotNull);
    listOfExpected.forEach(deal -> assertTrue(deal.getOpenTime().isAfter(date)));
  }

}
