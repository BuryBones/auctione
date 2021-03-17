package com.epam.marketplace.services;

import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.entities.Role;
import com.epam.marketplace.entities.User;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginService implements UserDetailsService {

  private final UserDao userDao;
  private final Logger logger;

  @Autowired
  public LoginService(UserDao userDao, Logger logger) {
    this.userDao = userDao;
    this.logger = logger;
  }

  @Override
  public org.springframework.security.core.userdetails.User loadUserByUsername(String login) throws UsernameNotFoundException {
    User user;
    Optional<User> optionalUser = userDao.findByLogin(login);
    if (optionalUser.isPresent()) {
      user = optionalUser.get();
      logger.info("Found user by login: " + login);
    } else {
      logger.info("User by login " + login + " not found!");
      throw new UsernameNotFoundException("NO USER FOUND!");
    }
    Set<Role> roles = user.getUserRoles();
    Set<GrantedAuthority> authorities = new HashSet<>();
    if (roles != null && !roles.isEmpty()) {
      logger.info("No roles found for " + login);
      for (Role role: roles) {
        logger.info("Role for " + login + ": " + role.getRoleName());
        GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
        authorities.add(authority);
      }
    } else {
      logger.info("No roles found for " + login);
    }

    org.springframework.security.core.userdetails.User result = new org.springframework.security.core.userdetails.User(
        user.getLogin(), user.getPassword(), authorities);
    return result;
  }
}
