package com.epam.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.epam.HibernateUtil;
import com.epam.dao.impl.DealDaoImpl;
import com.epam.dao.impl.ItemDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entities.Deal;
import com.epam.entities.Deal_;
import com.epam.entities.Item;
import com.epam.entities.User;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CommonDaoDealTest {

  private static UserDao userDao;
  private static ItemDao itemDao;
  private static DealDao dealDao;

  @BeforeAll
  private static void setup() {
    HibernateUtil.init();
    userDao = new UserDaoImpl();
    itemDao = new ItemDaoImpl();
    dealDao = new DealDaoImpl();
  }

  @Test
  public void findByIdDealTest() {
    // given
    Deal expected = new Deal();
    expected.setId(5);
    expected.setInitPrice(new BigDecimal(20000));
    expected.setOpenTime(LocalDateTime.of(2021,1,1,0,0));

    // when
    Optional<Deal> optionalDeal = dealDao.findById(5);

    // then
    assertTrue(optionalDeal.isPresent());
    assertNotNull(optionalDeal.get());
    assertEquals(optionalDeal.get(),expected);
  }

  @Test
  public void findAllDealTest() {
    // when
    List<Deal> allDeals = dealDao.findAll();

    // then
    assertEquals(allDeals.size(), 6);
    allDeals.forEach(Assertions::assertNotNull);
  }

  @Test
  public void saveDealTest() {
    // given
    Deal expected = new Deal();
    expected.setOpenTime(LocalDateTime.now());
    expected.setInitPrice(new BigDecimal(100000));
    expected.setCloseTime(LocalDateTime.now().plusSeconds(60));
    expected.setStatus(true);

    Optional<User> optionalUser = userDao.findById(1);
    if (optionalUser.isPresent()) {
      expected.setUser(optionalUser.get());
    } else {
      fail();
    }

    Optional<Item> optionalItem = itemDao.findById(1);
    if (optionalItem.isPresent()) {
      expected.setItem(optionalItem.get());
    } else {
      fail();
    }

    // when
    dealDao.save(expected);
    dealDao.refresh(expected);
    Deal actual = null;
    Optional<Deal> optionalDeal = dealDao.findById(expected.getId());
    if (optionalDeal.isPresent()) {
      actual = optionalDeal.get();
    } else {
      fail();
    }

    // then
    assertEquals(expected,actual);

    // cleanup
    dealDao.refresh(expected);
    dealDao.delete(expected);
  }

  @Test
  public void updateDealTest() {
    // given
    Optional<Deal> optionalDeal = dealDao.findById(6);
    Deal expected = null;
    if (optionalDeal.isPresent()) {
      expected = optionalDeal.get();
    } else {
      fail();
    }

    // when
    expected.setStatus(false);
    dealDao.update(expected);
    Optional<Deal> actual = dealDao.findById(6);

    // then
    assertTrue(actual.isPresent());
    assertNotNull(actual.get());
    assertEquals(expected,actual.get());

    // cleanup
    expected.setStatus(true);
    dealDao.update(expected);
  }

  @Test
  public void deleteDealTest() {
    // given
    Deal testDeal = new Deal();
    testDeal.setStatus(true);
    testDeal.setOpenTime(LocalDateTime.now());
    testDeal.setCloseTime(LocalDateTime.now().plusSeconds(60));
    testDeal.setInitPrice(new BigDecimal(100000));

    Optional<User> optionalUser = userDao.findById(1);
    if (optionalUser.isPresent()) {
      testDeal.setUser(optionalUser.get());
    } else {
      fail();
    }

    Optional<Item> optionalItem = itemDao.findById(1);
    if (optionalItem.isPresent()) {
      testDeal.setItem(optionalItem.get());
    } else {
      fail();
    }

    dealDao.save(testDeal);
    assertTrue(dealDao.findById(testDeal.getId()).isPresent());

    // when
    dealDao.delete(testDeal);

    // then
    assertTrue(dealDao.findById(testDeal.getId()).isEmpty());
  }

  @Test
  public void findByIdWithAttributesDealTest() {
    // when
    Optional<Deal> optionalDeal = dealDao.findByIdWithAttributes(
        1, Deal_.user,Deal_.item,Deal_.bids);

    // then
    assertTrue(optionalDeal.isPresent());
    Deal deal = optionalDeal.get();
    assertNotNull(deal);
    assertNotNull(deal.getUser());
    assertNotNull(deal.getItem());
    assertFalse(deal.getBids().isEmpty());
  }

}
