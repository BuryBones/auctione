package com.epam.marketplace.services;

import com.epam.marketplace.OperationResult;
import com.epam.marketplace.dao.RoleDao;
import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Role;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.validation.ConstraintsValidator;
import com.epam.marketplace.validation.logic.UserLogicValidator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

  private final UserDao userDao;
  private final RoleDao roleDao;
  private final CommonMapper mapper;
  // TODO: отедельный бин Бкрипт,
  private final PasswordEncoder passwordEncoder;
  private final ConstraintsValidator constraintsValidator;
  private final UserLogicValidator userLogicValidator;

  @Autowired
  public UserService(UserDao userDao, RoleDao roleDao,
      CommonMapper mapper,
      PasswordEncoder passwordEncoder,
      ConstraintsValidator constraintsValidator,
      @Lazy UserLogicValidator userLogicValidator) {
    this.userDao = userDao;
    this.roleDao = roleDao;
    this.mapper = mapper;
    this.passwordEncoder = passwordEncoder;
    this.constraintsValidator = constraintsValidator;
    this.userLogicValidator = userLogicValidator;
  }

  public List<UserDto> getUsers() {
    List<User> users = userDao.findAllWithRoles();
    ArrayList<UserDto> result = new ArrayList<>(users.size());
    for (User u: users) {
      result.add(mapper.getDtoFromEntity(u));
    }
    return result;
  }

  public OperationResult createUser(UserDto newBorn) {
    OperationResult validationResult = validate(newBorn);
    if (validationResult.getResult()) {
      User newUser = mapper.getEntityFromDto(newBorn);
      // TODO: find out how to add encoder ID (not like this)
      newUser.setPassword("{bcrypt}" + passwordEncoder.encode(newUser.getPassword()));
      setDefaultRole(newUser);
      return userDao.save(newUser);
    } else {
      return validationResult;
    }
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

  public OperationResult validate(UserDto userDto) {
    OperationResult constraintsValidationResult = constraintsValidator.validate(userDto);
    if (constraintsValidationResult.getResult()) {
      return userLogicValidator.validate(userDto);
    } else {
      return constraintsValidationResult;
    }
  }
}
