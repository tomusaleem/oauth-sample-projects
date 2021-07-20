package com.confiz.tarining.libraryservice.controller;

import com.confiz.tarining.libraryservice.response.BookResponse;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

  @GetMapping("/secret-books")
  public List<BookResponse> getSecretBooks() {
    BookResponse magicBook = new BookResponse("1", "7 Unique Magics", "Gill Bill",
        "Hidden book which explain 7 hidden magics");
    BookResponse timeTravelBook = new BookResponse("2", "Time travel", "Golkit Marasi",
        "How to travel is the past");
    return Arrays.asList(magicBook, timeTravelBook);
  }

  @GetMapping("/common-books")
  public List<BookResponse> getCommonBooks() {
    BookResponse independenceDay = new BookResponse("1", "Independence day", "kullu",
        "How people celebrate independence day");
    BookResponse mathBook = new BookResponse("2", "Simple Math", "Mr Khan",
        "Simple math concept with excercise");
    return Arrays.asList(independenceDay, mathBook);
  }

}
