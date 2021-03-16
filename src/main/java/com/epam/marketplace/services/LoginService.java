package com.epam.marketplace.services;

import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.entities.Role;
import com.epam.marketplace.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

  @Autowired
  public LoginService(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user;
    Optional<User> optionalUser = userDao.findByLogin(login);
    if (optionalUser.isPresent()) {
      user = optionalUser.get();
    } else {
      throw new UsernameNotFoundException("NO USER FOUND!");
    }

    Set<Role> roles = user.getUserRoles();
    List<GrantedAuthority> authorities = new ArrayList<>();
    if (roles != null && !roles.isEmpty()) {
      for (Role role: roles) {
        GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
        authorities.add(authority);
      }
    }

    UserDetails result = new org.springframework.security.core.userdetails.User(
        user.getLogin(), user.getPassword(), authorities);
    return result;
  }
}
