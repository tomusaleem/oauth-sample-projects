package com.confiz.training.PhotoAppWebClient.controller;

import com.confiz.training.PhotoAppWebClient.response.AlbumRest;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class AlbumController {

  @Autowired
  OAuth2AuthorizedClientService oauth2ClientService;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  WebClient webClient;

  @GetMapping("/albums")
  public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principle) {
    Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
    OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

    OAuth2AuthorizedClient oauth2Client = oauth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(),
        oauthToken.getName());

    String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
    System.out.println("jwtAccessToken = " + jwtAccessToken);


    System.out.println("Principle: " + principle);
    String tokenValue = principle.getIdToken().getTokenValue();
    System.out.println("Token: " + tokenValue);

    String url = "http://localhost:8082/albums";
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + jwtAccessToken);

    HttpEntity<List<AlbumRest>> entity = new HttpEntity<>(headers);


    ResponseEntity<List<AlbumRest>> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<AlbumRest>>() {});

    List<AlbumRest> albums = responseEntity.getBody();


//    AlbumRest album1 = new AlbumRest();
//    album1.setAlbumId("albumOne");
//    album1.setAlbumTitle("Album one title");
//    album1.setAlbumUrl("http://localhost:8082/albums/1");
//
//    AlbumRest album2 = new AlbumRest();
//    album2.setAlbumId("albumTwo");
//    album2.setAlbumTitle("Album two title");
//    album2.setAlbumUrl("http://localhost:8082/albums/2");

    model.addAttribute("albums", albums);

    return "albums";
  }

  @GetMapping("/albums-v2")
  public String getAlbumsV2(Model model, @AuthenticationPrincipal OidcUser principle) {

    String url = "http://localhost:8082/albums";
    List<AlbumRest> albums = webClient.get().uri(url).retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>() {
        }).block();
    model.addAttribute("albums", albums);

    return "albums";
  }
}
