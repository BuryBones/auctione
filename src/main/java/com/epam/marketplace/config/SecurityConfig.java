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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
    return new BCryptPasswordEncoder(BCryptVersion.$2A, 4);
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
    http.authorizeRequests()
        .antMatchers("/auctions", "/auctions/ajax", "/registration", "/validity-error")
        .permitAll();
    http.authorizeRequests()
        .antMatchers("/items/**", "/new-item/**", "/auctions/bid")
        .hasAnyRole("ADMIN", "USER");
    http.authorizeRequests()
        .antMatchers("/admin")
        .hasRole("ADMIN");
    http.authorizeRequests().and().formLogin()
        .loginProcessingUrl("/j_spring_security_check")
        .loginPage("/welcome")
        .defaultSuccessUrl("/auctions")
        .failureUrl("/welcome?error=true")
        .usernameParameter("login")
        .passwordParameter("password")
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET", false))
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true);
  }
}
