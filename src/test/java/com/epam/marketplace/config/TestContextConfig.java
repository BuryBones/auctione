package com.epam.marketplace.config;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dao.ItemDao;
import com.epam.marketplace.dao.RoleDao;
import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.dao.impl.BidDaoImpl;
import com.epam.marketplace.dao.impl.DealDaoImpl;
import com.epam.marketplace.dao.impl.ItemDaoImpl;
import com.epam.marketplace.dao.impl.RoleDaoImpl;
import com.epam.marketplace.dao.impl.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContextConfig {

  @Bean
  public BidDao getBidDao() {
    return new BidDaoImpl();
  }

  @Bean
  public DealDao getDealDao() {
    return new DealDaoImpl();
  }

  @Bean
  public ItemDao getItemDao() {
    return new ItemDaoImpl();
  }

  @Bean
  public RoleDao getRoleDao() {
    return new RoleDaoImpl();
  }

  @Bean
  public UserDao getUserDao() {
    return new UserDaoImpl();
  }
}
