package com.epam.marketplace.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.epam.marketplace.config.TestContextConfig;
import com.epam.marketplace.entities.Role;
import com.epam.marketplace.entities.Role_;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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
public class CommonDaoRoleTest {

  @Autowired
  private RoleDao roleDao;

  @Test
  public void findByIdRoleTest() {
    // given
    Role expected = new Role();
    expected.setId(2);
    expected.setRoleName("REGULAR_USER");

    // when
    Optional<Role> optionalRole = roleDao.findById(2);

    // then
    assertTrue(optionalRole.isPresent());
    assertNotNull(optionalRole.get());
    assertEquals(optionalRole.get(), expected);
  }

  @Test
  public void findAllRoleTest() {
    // when
    List<Role> allRoles = roleDao.findAll();

    // then
    assertEquals(3, allRoles.size());
    allRoles.forEach(Assertions::assertNotNull);
  }

  @Test
  public void saveRoleTest() {
    // given
    Role expected = new Role();
    expected.setRoleName("SAVE_TEST_ROLE");

    // when
    roleDao.save(expected);
    roleDao.refresh(expected);
    Role actual = null;
    Optional<Role> optionalRole = roleDao.findByName("SAVE_TEST_ROLE");
    if (optionalRole.isPresent()) {
      actual = optionalRole.get();
    } else {
      fail();
    }

    // then
    assertEquals(expected, actual);

    // cleanup
    roleDao.delete(expected);
  }

  @Disabled("IGNORED: Test changes NaturalID")
  @Test
  public void updateRoleTest() {
    // given
    Optional<Role> optionalRole = roleDao.findByName("TEST_ROLE");
    Role expected = null;
    if (optionalRole.isPresent()) {
      expected = optionalRole.get();
    } else {
      fail();
    }

    // when
    expected.setRoleName("TEST_ROLE_CHANGED");
    roleDao.update(expected);
    Optional<Role> actual = roleDao.findByName("TEST_ROLE_CHANGED");

    // then
    assertTrue(actual.isPresent());
    assertNotNull(actual.get());
    assertEquals(expected, actual.get());
  }

  @Test
  public void deleteRoleTest() {
    // given
    Role testRole = new Role();
    testRole.setRoleName("TO DELETE");

    roleDao.save(testRole);
    assertTrue(roleDao.findByName("TO DELETE").isPresent());

    // when
    roleDao.delete(testRole);

    // then
    assertTrue(roleDao.findByName("TO DELETE").isEmpty());
  }

  @Test
  public void findByIdWithAttributesRoleTest() {
    // when
    Optional<Role> optionalRole = roleDao.findByIdWithAttributes(
        2, Role_.users);

    // then
    assertTrue(optionalRole.isPresent());
    Role role = optionalRole.get();
    assertNotNull(role);
    assertFalse(role.getUsers().isEmpty());
  }
}
