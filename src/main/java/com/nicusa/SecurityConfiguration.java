package com.nicusa;

import com.nicusa.controller.SecurityController;
import com.nicusa.security.UserProfileSignInAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


  @Inject
  private DataSource dataSource;

  @Inject
  private SecurityController securityController;

  @Autowired
  public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
      .dataSource(dataSource)
      .usersByUsernameQuery("select userid, userid, true from userprofile where userid = ?")
      .authoritiesByUsernameQuery("select 'ROLE_USER' from DUAL")
      .passwordEncoder(passwordEncoder());
  }


  @Override
  public void configure(WebSecurity web) {
    web
      .ignoring()
      .antMatchers("/**/*.css", "/**/*.png", "/**/*.gif", "/**/*.jpg", "/**/*.js","/**/*.map");

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
      .formLogin()
      .loginPage("/signin")
      .loginProcessingUrl("/signin/authenticate")
      .failureUrl("/signin?param.error=bad_credentials")
      .and()
      .logout().permitAll()
      .logoutUrl("/signout")
      .deleteCookies("JSESSIONID")
      .and()
      .authorizeRequests()
      .antMatchers("/search/**", "/signin/**", "/error", "/signup/**", "/autocomplete", "/drug/**", "/enforcement/**", "/event/**",
        "/recalls/**", "/user/**", "/", "/index.html" ,"/recalls" ,"/drug/recalls", "/drug/enforcements").permitAll()
      .antMatchers("/**").authenticated()
      .and()
      .rememberMe();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public RequestCache requestCache() {
    return new HttpSessionRequestCache();
  }

  @Bean
  public SignInAdapter signInAdapter() {
    return new UserProfileSignInAdapter();
  }

  @Bean
  public ProviderSignInUtils providerSignInUtils() {
    return new ProviderSignInUtils();
  }

}
