package com.promineotech.story.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.story.entity.Book;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultStorytellingBookDao implements StorytellingBookDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Book> fetchBooks(String seriesName) {
    log.info("DAO: seriesName={}", seriesName);

    //@formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM book "
        + "WHERE series_name = :series_name";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("series_name", seriesName);

    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      @Override
      public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        //@formatter:off
        return Book.builder()
          .seriesName(rs.getString("series_name"))
          .bookTitle(rs.getString("book_title"))
          .seriesBookOrder(rs.getInt("series_book_order"))
          .author(rs.getString("author"))
          .bookId(rs.getLong("book_id"))
          .numOfChapters(rs.getInt("num_of_chapters"))
          .releaseDate(rs.getDate("release_date"))
          .build();
        //@formatter:on
      }
    });
  }

  @Override
  public Book createBook(String bookTitle, String seriesName, int seriesBookOrder, String author,
      int numOfChapters, Date releaseDate) {
    log.info(
        "DAO: bookTitle={}, seriesName={}, seriesBookOrder={}, author={}, numOfChapters={}, releaseDate={}",
        bookTitle, seriesName, seriesBookOrder, author, numOfChapters, releaseDate);

    //@formatter:off
    String sql = ""
        + "INSERT INTO book "
        + "(series_name, book_title, series_book_order, author, num_of_chapters, release_date) "
        + "VALUES "
        + "(:series_name, :book_title, :series_book_order, :author, :num_of_chapters, :release_date)";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();

    params.put("series_name", seriesName);
    params.put("book_title", bookTitle);
    params.put("series_book_order", seriesBookOrder);
    params.put("author", author);
    params.put("num_of_chapters", numOfChapters);
    params.put("release_date", releaseDate);

    jdbcTemplate.update(sql, params);

    return null;
  }

  @Override
  public Book updateSeriesBookOrder(String bookTitle, int seriesBookOrder) {
    log.info("DAO: bookTitle={}, seriesBookOrder={}", bookTitle, seriesBookOrder);

  //@formatter:off
    String sql = ""
        + "UPDATE book "
        + "SET series_book_order = :series_book_order "
        + "WHERE book_title = :book_title";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();

    params.put("series_book_order", seriesBookOrder);
    params.put("book_title", bookTitle);

    jdbcTemplate.update(sql, params);

    return null;
  }

  @Override
  public Book deleteBook(String bookTitle) {
    log.info("DAO: bookTitle={}", bookTitle);

  //@formatter:off
    String sql = ""
        + "DELETE FROM book "
        + "WHERE book_title = :book_title";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("book_title", bookTitle);

    jdbcTemplate.update(sql, params);

    return null;
  }


}
