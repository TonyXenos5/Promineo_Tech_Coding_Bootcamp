package com.promineotech.story.service;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.story.dao.StorytellingBookDao;
import com.promineotech.story.entity.Book;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultStorytellingBookService implements StorytellingBookService {

  @Autowired
  private StorytellingBookDao storytellingBookDao;

  @Transactional(readOnly = true)
  @Override
  public List<Book> fetchBooks(String seriesName) {
    log.info("The fetchBooks method was called with seriesName={}", seriesName);
    List<Book> books = storytellingBookDao.fetchBooks(seriesName);

    if (books.isEmpty()) {
      String msg = String.format("No books found with seriesName=%s", seriesName);

      throw new NoSuchElementException(msg);
    }

    return books;

  }

  @Transactional
  @Override
  public Book createBook(String bookTitle, String seriesName, int seriesBookOrder, String author,
      int numOfChapters, Date releaseDate) {
    log.info(
        "The createBook method was called with bookTitle={}, seriesName={}, seriesBookOrder={},"
            + "author={}, numOfChapters={}, releaseDate={}",
        bookTitle, seriesName, seriesBookOrder, author, numOfChapters, releaseDate);
    return storytellingBookDao.createBook(bookTitle, seriesName, seriesBookOrder, author,
        numOfChapters, releaseDate);
  }

  @Transactional
  @Override
  public Book updateSeriesBookOrder(String bookTitle, int seriesBookOrder) {
    log.info("The updateSeriesBookOrder method was called with bookTitle={}, seriesBookOrder={}",
        bookTitle, seriesBookOrder);
    return storytellingBookDao.updateSeriesBookOrder(bookTitle, seriesBookOrder);
  }

  @Transactional
  @Override
  public Book deleteBook(String bookTitle) {
    log.info("The deleteBook method was called with bookTitle={}", bookTitle);
    return storytellingBookDao.deleteBook(bookTitle);
  }

}
