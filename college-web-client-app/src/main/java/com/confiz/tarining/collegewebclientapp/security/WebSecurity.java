package com.confiz.tarining.collegewebclientapp.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  private final InMemoryClientRegistrationRepository registrationRepository;

  public WebSecurity(InMemoryClientRegistrationRepository registrationRepository) {
    this.registrationRepository = registrationRepository;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/").permitAll()
        .anyRequest().authenticated().and().oauth2Login()
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .deleteCookies("JSESSIONID");
//        .logout(logout -> {
//          logout.logoutSuccessHandler(logoutSuccessHandler());
//          logout.invalidateHttpSession(true);
//          logout.clearAuthentication(true);
//          logout.deleteCookies("JSESSIONID");
//        });
  }

  private LogoutSuccessHandler logoutSuccessHandler() {

    OidcClientInitiatedLogoutSuccessHandler handler =
        new OidcClientInitiatedLogoutSuccessHandler(registrationRepository);
    handler.setPostLogoutRedirectUri("http://localhost:8002/");

    return handler;
  }
}
