package main.java.com.epam;

import main.java.com.epam.entities.Bid;
import main.java.com.epam.entities.Deal;
import main.java.com.epam.entities.Item;
import main.java.com.epam.entities.Role;
import main.java.com.epam.entities.User;
import main.java.com.epam.implementations.GenericDaoImpl;

public class Application {

  public static void main(String[] args) {

    GenericDaoImpl<User> userDao = new GenericDaoImpl<>(User.class);
    userDao.findAll().forEach(System.out::println);

    GenericDaoImpl<Role> roleDao = new GenericDaoImpl<>(Role.class);
    roleDao.findAll().forEach(System.out::println);

    GenericDaoImpl<Item> itemDao = new GenericDaoImpl<>(Item.class);
    itemDao.findAll().forEach(System.out::println);

    GenericDaoImpl<Deal> dealDao = new GenericDaoImpl<>(Deal.class);
    dealDao.findAll().forEach(System.out::println);

    GenericDaoImpl<Bid> bidDao = new GenericDaoImpl<>(Bid.class);
    bidDao.findAll().forEach(System.out::println);

  }
}
