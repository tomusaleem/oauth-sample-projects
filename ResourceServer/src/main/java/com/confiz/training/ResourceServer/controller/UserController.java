package com.confiz.training.ResourceServer.controller;

import com.confiz.training.ResourceServer.response.UserReset;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @GetMapping("/status/check")
  public String checkStatus() {
    return "Working...";
  }

  @PreAuthorize("#id == #jwt.subject")
//  @Secured("ROLE_developer")
  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
    return "User id = " + id + " and JWT subject " + jwt.getSubject();
  }

  @PostAuthorize("returnObject.userId == #jwt.subject")
  @GetMapping("/{id}")
  public UserReset getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
    return new UserReset("Muhammad", "Usman", "b12a1540-6f32-40e6-9272-03394606e0d0");
  }




}
