package main.java.com.epam;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import main.java.com.epam.dao.BidDao;
import main.java.com.epam.dao.DealDao;
import main.java.com.epam.dao.impl.BidDaoImpl;
import main.java.com.epam.dao.impl.DealDaoImpl;
import main.java.com.epam.dao.impl.UserDaoImpl;
import main.java.com.epam.entities.Bid;
import main.java.com.epam.entities.Deal;

public class Application {

  public static void main(String[] args) {

    // GenericDaoImpl test of findAll()
//    GenericDaoImpl<User> userDao = new GenericDaoImpl<>(User.class);
//    userDao.findAll().forEach(System.out::println);
//
//    GenericDaoImpl<Role> roleDao = new GenericDaoImpl<>(Role.class);
//    roleDao.findAll().forEach(System.out::println);
//
//    GenericDaoImpl<Item> itemDao = new GenericDaoImpl<>(Item.class);
//    itemDao.findAll().forEach(System.out::println);
//
//    GenericDaoImpl<Deal> dealDao = new GenericDaoImpl<>(Deal.class);
//    dealDao.findAll().forEach(System.out::println);
//
//    GenericDaoImpl<Bid> bidDao = new GenericDaoImpl<>(Bid.class);
//    bidDao.findAll().forEach(System.out::println);

    // ItemDao test of getItemByName()
//    ItemDaoImpl itemDao = new ItemDaoImpl();
//    System.out.println(itemDao.getItemByName("Глобус Украины"));

    // BidDao test of getBidsFromDate()
//    BidDao bidDao = new BidDaoImpl();
//    DealDao dealDao = new DealDaoImpl();
//
//    bidDao.findAll().forEach(System.out::println);
//
//    Bid newBid = new Bid();
//    Deal deal = dealDao.findById(1);
//
//    newBid.setDeal(deal);
//    newBid.setUser(new UserDaoImpl().findById(8));
//    newBid.setOffer(deal.getInitPrice().add(BigDecimal.valueOf(100)));
//    newBid.setDateAndTime(new Timestamp(System.currentTimeMillis()));
//    bidDao.save(newBid);
//
//    bidDao.findAll().forEach(System.out::println);
//
//    System.out.println("---------------------");
//
//    bidDao.getBidsFromDate(new Date(121, Calendar.JANUARY,25)).forEach(System.out::println);

    DealDao dealDao = new DealDaoImpl();

    System.out.println("ALL DEALS:");
    dealDao.findAll().forEach(System.out::println);
    System.out.println("--------------");
    System.out.println("ONLY CLOSED DEALS:");
    dealDao.getDealsByStatus(false).forEach(System.out::println);
  }
}
