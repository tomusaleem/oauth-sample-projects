package com.confiz.tarining.collegewebclientapp.controllers;

import com.confiz.tarining.collegewebclientapp.response.BookResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class BookController {

  @Autowired
  WebClient webClient;

  @GetMapping("/secret-books")
  public String getBooks(Model model, @AuthenticationPrincipal OidcUser principle) {
    String url = "http://localhost:8081/books/secret-books";
    List<BookResponse> books = webClient.get().uri(url).retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<BookResponse>>() {
        }).block();
    model.addAttribute("books", books);

    return "books";
  }

  @GetMapping("/common-books")
  public String getCommonBooks(Model model, @AuthenticationPrincipal OidcUser principle) {
    String url = "http://localhost:8081/books/common-books";
    List<BookResponse> books = webClient.get().uri(url).retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<BookResponse>>() {
        }).block();
    model.addAttribute("books", books);

    return "books";
  }
}
