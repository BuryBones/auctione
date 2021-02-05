package com.epam.marketplace.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.impl.RoleDaoImpl;
import com.epam.marketplace.entities.Role;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RoleDaoTest {

  private static RoleDao roleDao;

  @BeforeAll
  private static void setup() {
    HibernateUtil.init();
    roleDao = new RoleDaoImpl();
  }

  @Test
  public void findByNameSuccessTest() {
    // when
    Optional<Role> optionalRole = roleDao.findByName("ADMIN");

    // then
    assertTrue(optionalRole.isPresent());
  }

  @Test
  public void findByNameFailTest() {
    // when
    Optional<Role> optionalRole = roleDao.findByName("no such role");

    // then
    assertFalse(optionalRole.isPresent());
  }

}
