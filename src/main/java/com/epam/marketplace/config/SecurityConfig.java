package com.epam.marketplace.config;

import com.epam.marketplace.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private LoginService loginService;

  @Autowired
  public void setLoginService(LoginService loginService) {
    this.loginService = loginService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
    builder.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    builder.authenticationProvider(authenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(loginService);
    return authenticationProvider;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests().antMatchers("/admin*").hasRole("ADMIN");
    http.authorizeRequests().antMatchers("/items*","/new-item*")
        .hasAnyRole("ADMIN","USER");
    http.authorizeRequests().antMatchers("/auctions","/registration").permitAll();
    http.authorizeRequests().and().formLogin()
        .loginProcessingUrl("/j_spring_security_check")
        .loginPage("/welcome")
        .defaultSuccessUrl("/auctions")
        .failureUrl("/welcome?error=true")
        .usernameParameter("login")
        .passwordParameter("password")
        .and()
        .logout()
        .logoutUrl("/j_spring_security_logout")
        .logoutSuccessUrl("/welcome")
        .invalidateHttpSession(true);
  }
}
