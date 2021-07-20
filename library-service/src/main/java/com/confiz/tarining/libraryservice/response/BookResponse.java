package com.confiz.tarining.libraryservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

  private String id;
  private String title;
  private String author;
  private String description;

}
