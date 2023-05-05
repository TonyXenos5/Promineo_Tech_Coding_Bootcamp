package com.promineotech.story.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.story.entity.Genre;
import com.promineotech.story.entity.Series;
import com.promineotech.story.entity.SeriesGenre;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultStorytellingSeriesDao implements StorytellingSeriesDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Series> fetchSeriesInfo(String seriesName) {
    log.info("DAO seriesName={}", seriesName);
    //@formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM series "
        + "WHERE series_name = :series_name";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("series_name", seriesName);

    return jdbcTemplate.query(sql, params, new RowMapper<>() {

      @Override
      public Series mapRow(ResultSet rs, int rowNum) throws SQLException {
        //@formatter:off
        return Series.builder()
            .seriesId(rs.getLong("series_id"))
            .seriesName(rs.getString("series_name"))
            .summary(rs.getString("summary"))
            .numOfBooks(rs.getInt("num_of_books"))
            .build();
        //@formatter:on
      }
    });
  }

  @Override
  public Series createSeries(String seriesName, String summary, int numOfBooks) {
    log.info("DAO: seriesName={}, summary={}, numOfBooks={}", seriesName, summary, numOfBooks);

    //@formatter:off
    String sql = ""
        + "INSERT INTO series "
        + "(series_name, summary, num_of_books) "
        + "VALUES "
        + "(:series_name, :summary, :num_of_books)";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();

    params.put("series_name", seriesName);
    params.put("summary", summary);
    params.put("num_of_books", numOfBooks);

    jdbcTemplate.update(sql, params);

    return null;
  }

  @Override
  public SeriesGenre linkSeriesAndGenre(String seriesName, String genreName) {
    //@formatter:off
    String sqlSeries = ""
        + "SELECT series_id "
        + "FROM series "
        + "WHERE series_name = :series_name";
    //@formatter:on

    Map<String, Object> paramsSql = new HashMap<>();

    paramsSql.put("series_name", seriesName);

    Series seriesId = jdbcTemplate.query(sqlSeries, paramsSql, new SeriesResultSetExtractor());

    //@formatter:off
    String sqlGenre = ""
        + "SELECT genre_id "
        + "FROM genre "
        + "WHERE genre_name = :genre_name";
    //@formatter:on

    Map<String, Object> paramsGenre = new HashMap<>();

    paramsGenre.put("genre_name", genreName);

    Genre genreId = jdbcTemplate.query(sqlGenre, paramsGenre, new GenreResultSetExtractor());

    return sqlSeriesGenre(seriesId, genreId);
  }

  class SeriesResultSetExtractor implements ResultSetExtractor<Series> {

    @Override
    public Series extractData(ResultSet rs) throws SQLException, DataAccessException {
      rs.next();
      //@formatter:off
      return Series.builder()
          .seriesId(rs.getLong("series_id"))
          .build();
      //@formatter:on
    }

  }

  class GenreResultSetExtractor implements ResultSetExtractor<Genre> {

    @Override
    public Genre extractData(ResultSet rs) throws SQLException, DataAccessException {
      rs.next();
      //@formatter:off
      return Genre.builder()
          .genreId(rs.getLong("genre_id"))
          .build();
      //@formatter:on
    }

  }

  protected SeriesGenre sqlSeriesGenre(Series seriesId, Genre genreId) {

    String series = seriesId.toString().replace("Series(seriesId=", "")
        .replace(", seriesName=null, summary=null, numOfBooks=0)", "");
    String genre =
        genreId.toString().replace("Genre(genreId=", "").replace(", genreName=null)", "");

    //@formatter:off
    String sql = ""
        + "INSERT INTO series_genre ("
        + "series_id, genre_id"
        + ") VALUES ("
        + ":series_id, :genre_id"
        + ")";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();

    params.put("series_id", series);
    params.put("genre_id", genre);

    jdbcTemplate.update(sql, params);

    return null;
  }

}
