package com.epam.marketplace.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.marketplace.config.TestContextConfig;
import com.epam.marketplace.entities.Role;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith({H2Extension.class, SpringExtension.class})
@ContextConfiguration(
    classes = TestContextConfig.class,
    loader = AnnotationConfigContextLoader.class)
public class RoleDaoTest {

  @Autowired
  private RoleDao roleDao;

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
