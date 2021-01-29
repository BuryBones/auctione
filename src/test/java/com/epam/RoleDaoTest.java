package com.epam;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.dao.RoleDao;
import com.epam.dao.impl.RoleDaoImpl;
import com.epam.entities.Role;
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
