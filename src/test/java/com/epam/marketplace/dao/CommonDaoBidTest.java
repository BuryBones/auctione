package com.epam.marketplace.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.impl.BidDaoImpl;
import com.epam.marketplace.dao.impl.DealDaoImpl;
import com.epam.marketplace.dao.impl.UserDaoImpl;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Bid_;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(H2Extension.class)
public class CommonDaoBidTest {

  private static UserDao userDao;
  private static DealDao dealDao;
  private static BidDao bidDao;

  @BeforeAll
  private static void setup() {
    HibernateUtil.init();
    userDao = new UserDaoImpl();
    dealDao = new DealDaoImpl();
    bidDao = new BidDaoImpl();
  }

  @Test
  public void findByIdBidTest() {
    // given
    Bid expected = new Bid();
    expected.setId(5);
    expected.setDateAndTime(LocalDateTime.of(2021, 1, 2, 0, 0));
    expected.setOffer(new BigDecimal(21000));

    // when
    Optional<Bid> optionalBid = bidDao.findById(5);

    // then
    assertTrue(optionalBid.isPresent());
    assertNotNull(optionalBid.get());
    assertEquals(optionalBid.get(), expected);
  }

  @Test
  public void findAllBidTest() {
    // when
    List<Bid> allBids = bidDao.findAll();

    // then
    assertEquals(allBids.size(), 5);
    allBids.forEach(Assertions::assertNotNull);
  }

  @Test
  public void saveBidTest() {
    // given
    Bid expected = new Bid();
    expected.setOffer(new BigDecimal(10000));
    expected.setDateAndTime(LocalDateTime.now());

    Optional<User> optionalUser = userDao.findById(1);
    if (optionalUser.isPresent()) {
      expected.setUser(optionalUser.get());
    } else {
      fail();
    }

    Optional<Deal> optionalDeal = dealDao.findById(1);
    if (optionalDeal.isPresent()) {
      expected.setDeal(optionalDeal.get());
    } else {
      fail();
    }

    // when
    bidDao.save(expected);
    bidDao.refresh(expected);
    Bid actual = null;
    Optional<Bid> optionalBid = bidDao.findById(expected.getId());
    if (optionalBid.isPresent()) {
      actual = optionalBid.get();
    } else {
      fail();
    }

    // then
    assertEquals(expected, actual);

    // cleanup
    bidDao.refresh(expected);
    bidDao.delete(expected);
  }

  @Test
  public void updateBidTest() {
    // given
    Optional<Bid> optionalBid = bidDao.findById(1);
    Bid expected = null;
    if (optionalBid.isPresent()) {
      expected = optionalBid.get();
    } else {
      fail();
    }

    // when
    expected.setOffer(expected.getOffer().add(BigDecimal.valueOf(100)));
    bidDao.update(expected);
    Optional<Bid> actual = bidDao.findById(1);

    // then
    assertTrue(actual.isPresent());
    assertNotNull(actual.get());
    assertEquals(expected, actual.get());

    // cleanup
    expected.setOffer(expected.getOffer().subtract(BigDecimal.valueOf(100)));
    bidDao.update(expected);
  }

  @Test
  public void deleteBidTest() {
    // given
    Bid testBid = new Bid();
    testBid.setDateAndTime(LocalDateTime.now());
    testBid.setOffer(BigDecimal.valueOf(100000));

    Optional<User> optionalUser = userDao.findById(1);
    if (optionalUser.isPresent()) {
      testBid.setUser(optionalUser.get());
    } else {
      fail();
    }

    Optional<Deal> optionalDeal = dealDao.findById(1);
    if (optionalDeal.isPresent()) {
      testBid.setDeal(optionalDeal.get());
    } else {
      fail();
    }

    bidDao.save(testBid);
    assertTrue(bidDao.findById(testBid.getId()).isPresent());

    // when
    bidDao.delete(testBid);

    // then
    assertTrue(bidDao.findById(testBid.getId()).isEmpty());
  }

  @Test
  public void findByIdWithAttributesBidTest() {
    // when
    Optional<Bid> optionalBid = bidDao.findByIdWithAttributes(
        1, Bid_.user, Bid_.deal);

    // then
    assertTrue(optionalBid.isPresent());
    Bid bid = optionalBid.get();
    assertNotNull(bid);
    assertNotNull(bid.getUser());
    assertNotNull(bid.getDeal());
  }
}
