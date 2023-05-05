package com.promineotech.story.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.story.entity.Genre;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultStorytellingGenreDao implements StorytellingGenreDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Genre> fetchGenreInfo(String genreName) {
    log.info("DAO genreName={}", genreName);
    //@formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM genre "
        + "WHERE genre_name = :genre_name";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("genre_name", genreName);

    return jdbcTemplate.query(sql, params, new RowMapper<>() {

      @Override
      public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        //@formatter:off
        return Genre.builder()
            .genreName(rs.getString("genre_name"))
            .genreId(rs.getLong("genre_id"))
            .build();
        //@formatter:on
      }
    });
  }

  @Override
  public Genre createGenre(String genreName) {
    log.info("DAO: genreName={}", genreName);

    //@formatter:off
    String sql = ""
        + "INSERT INTO genre "
        + "(genre_name) "
        + "VALUES "
        + "(:genre_name)";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();

    params.put("genre_name", genreName);

    jdbcTemplate.update(sql, params);

    return null;
  }

}
