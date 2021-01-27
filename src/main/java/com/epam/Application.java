package com.epam;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import com.epam.dao.*;
import com.epam.dao.impl.*;
import com.epam.entities.*;

public class Application {

  public static void main(String[] args) {

    UserDao userDao = new UserDaoImpl();
    RoleDao roleDao = new RoleDaoImpl();
    ItemDaoImpl itemDao = new ItemDaoImpl();
    DealDao dealDao = new DealDaoImpl();
    BidDao bidDao = new BidDaoImpl();

  }
}
