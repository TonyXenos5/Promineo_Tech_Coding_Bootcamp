package com.promineotech.story.dao;

import java.sql.Date;
import java.util.List;
import com.promineotech.story.entity.Book;

public interface StorytellingBookDao {

  List<Book> fetchBooks(String seriesName);

  Book createBook(String bookTitle, String seriesName, int seriesBookOrder, String author,
      int numOfChapters, Date releaseDate);

  Book updateSeriesBookOrder(String bookTitle, int seriesBookOrder);

  Book deleteBook(String bookTitle);

}
