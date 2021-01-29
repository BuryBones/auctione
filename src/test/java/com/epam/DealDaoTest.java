package com.epam;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.dao.DealDao;
import com.epam.dao.impl.DealDaoImpl;
import com.epam.entities.Deal;
import java.util.Calendar;
import java.util.Date;
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
  public void findByStatusTest() {
    // when

  }

  @Test
  public void findFromDateTest() {
    // when
    Date date = new Date(121,Calendar.JANUARY,2);
    List<Deal> listOfExpected = dealDao.findFromDate(date);
    List<Deal> listOfAll = dealDao.findAll();

    // then
    assertTrue(listOfAll.stream()                             // check if there any deal before
        .anyMatch(deal -> deal.getOpenTime().before(date))); // the date among all deals
    listOfExpected.forEach(Assertions::assertNotNull);
    listOfExpected.forEach(deal -> assertTrue(deal.getOpenTime().after(date)));
  }

}
