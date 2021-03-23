package com.epam.marketplace.services;

import com.epam.marketplace.dao.RoleDao;
import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Role;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.exceptions.validity.ValidityException;
import com.epam.marketplace.validation.logic.ValidatorType;
import com.epam.marketplace.validation.logic.user.AbstractUserLogicValidator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final Logger logger = Logger.getLogger("application");
  private final UserDao userDao;
  private final RoleDao roleDao;
  private final CommonMapper mapper;
  // TODO: отедельный бин Бкрипт,
  private final PasswordEncoder passwordEncoder;
  private final BeanFactory beanFactory;
  private List<AbstractUserLogicValidator> validators;

  @Autowired
  public UserService(UserDao userDao, RoleDao roleDao,
      CommonMapper mapper,
      PasswordEncoder passwordEncoder,
      BeanFactory beanFactory) {
    this.userDao = userDao;
    this.roleDao = roleDao;
    this.mapper = mapper;
    this.passwordEncoder = passwordEncoder;
    this.beanFactory = beanFactory;
  }

  public List<UserDto> getUsers() {
    List<User> users = userDao.findAllWithRoles();
    ArrayList<UserDto> result = new ArrayList<>(users.size());
    for (User u : users) {
      result.add(mapper.getDtoFromEntity(u));
    }
    return result;
  }

  public void createUser(UserDto newBorn) throws ValidityException {
    for (AbstractUserLogicValidator validator : validators) {
      logger.info("Validating with " + validator.getClass().getName());
      validator.validate(newBorn);
    }
    User newUser = mapper.getEntityFromDto(newBorn);
    // TODO: find out how to add encoder ID (not like this)
    newUser.setPassword("{bcrypt}" + passwordEncoder.encode(newUser.getPassword()));
    setDefaultRole(newUser);
    userDao.save(newUser);
  }

  public boolean checkIfUserExistsByLogin(String login) {
    return userDao.findByLogin(login).isPresent();
  }

  public boolean checkIfEmailAlreadyRegistered(String email) {
    return userDao.findByEmail(email).isPresent();
  }

  public String getCurrentUserName() {
    Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (obj instanceof User) {
      User user = (User) obj;
      return user.getLastName() + " " + user.getFirstName();
    } else {
      return "Guest";
    }
  }

  public int getCurrentUserId() {
    Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (obj instanceof User) {
      User user = (User) obj;
      return user.getId();
    } else {
      return 0;
    }
  }

  private void setDefaultRole(User user) {
    HashSet<Role> roles = new HashSet<>();
    roles.add(roleDao.findById(2).get());
    user.setUserRoles(roles);
  }

  @PostConstruct
  private void initValidators() {
    validators = (List<AbstractUserLogicValidator>)
        beanFactory.getBean("validators", ValidatorType.USER);
    logger.info("User service got " + validators.size() + " validators");
  }
}
