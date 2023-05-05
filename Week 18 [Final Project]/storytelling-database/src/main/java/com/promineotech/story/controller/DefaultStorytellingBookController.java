package com.promineotech.story.controller;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.story.entity.Book;
import com.promineotech.story.service.StorytellingBookService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultStorytellingBookController implements StorytellingBookController {

  @Autowired
  private StorytellingBookService storytellingBookService;

  @Override
  public List<Book> fetchBooks(String seriesName) {
    log.info("seriesName={}", seriesName);
    return storytellingBookService.fetchBooks(seriesName);
  }

  @Override
  public Book createBook(String bookTitle, String seriesName, int seriesBookOrder, String author,
      int numOfChapters, Date releaseDate) {
    log.info(
        "bookTitle={}, seriesName={}, seriesBookOrder={}, author={}, numOfChapters={}, releaseDate={}",
        bookTitle, seriesName, seriesBookOrder, author, numOfChapters, releaseDate);
    return storytellingBookService.createBook(bookTitle, seriesName, seriesBookOrder, author,
        numOfChapters, releaseDate);
  }

  @Override
  public Book updateSeriesBookOrder(String bookTitle, int seriesBookOrder) {
    log.info("bookTitle={}, seriesBookOrder={}", bookTitle, seriesBookOrder);
    return storytellingBookService.updateSeriesBookOrder(bookTitle, seriesBookOrder);
  }

  @Override
  public Book deleteBook(String bookTitle) {
    log.info("bookTitle={}", bookTitle);
    return storytellingBookService.deleteBook(bookTitle);
  }
}
