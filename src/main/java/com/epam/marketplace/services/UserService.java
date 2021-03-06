package com.epam.marketplace.services;

import com.epam.marketplace.dao.RoleDao;
import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.dto.Dto;
import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.entities.Role;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.exceptions.validity.ValidityException;
import com.epam.marketplace.validation.logic.LogicValidator;
import com.epam.marketplace.validation.logic.user.AbstractUserLogicValidator;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import ma.glasnost.orika.BoundMapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final Logger logger = Logger.getLogger("application");
  private final UserDao userDao;
  private final RoleDao roleDao;
  private final BoundMapperFacade<User, UserDto> mapper;
  private final PasswordEncoder passwordEncoder;

  private final List<LogicValidator<? extends Dto>> validators;

  @Autowired
  public UserService(UserDao userDao, RoleDao roleDao,
      BoundMapperFacade<User, UserDto> mapper,
      PasswordEncoder passwordEncoder,
      @Qualifier("userValidators") List<LogicValidator<? extends Dto>> validators) {
    this.userDao = userDao;
    this.roleDao = roleDao;
    this.mapper = mapper;
    this.passwordEncoder = passwordEncoder;
    this.validators = validators;
  }

  public List<UserDto> getUsers() {
    List<User> users = userDao.findAllWithRoles();
    return users.stream().map(mapper::map).collect(Collectors.toList());
  }

  public void createUser(UserDto newBorn) throws ValidityException {
    validate(newBorn);
    User newUser = mapper.mapReverse(newBorn);
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
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

  private void validate(UserDto newBorn) throws ValidityException {
    for (LogicValidator<? extends Dto> validatorInterface : validators) {
      AbstractUserLogicValidator userValidator = (AbstractUserLogicValidator) validatorInterface;
      logger.info("Validating with " + userValidator.getClass().getName());
      userValidator.validate(newBorn);
    }
  }
}
