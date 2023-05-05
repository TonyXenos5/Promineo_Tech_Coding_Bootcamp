package com.promineotech.story.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.story.dao.StorytellingGenreDao;
import com.promineotech.story.entity.Genre;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultStorytellingGenreService implements StorytellingGenreService {

  @Autowired
  private StorytellingGenreDao storytellingGenreDao;

  @Transactional(readOnly = true)
  @Override
  public List<Genre> fetchGenreInfo(String genreName) {
    log.info("The fetchGenreInfo method was called with genreName={}", genreName);

    List<Genre> genres = storytellingGenreDao.fetchGenreInfo(genreName);

    if (genres.isEmpty()) {
      String msg = String.format("No genres found with genreName=%s", genreName);

      throw new NoSuchElementException(msg);
    }

    return genres;
  }

  @Transactional
  @Override
  public Genre createGenre(String genreName) {
    log.info("The createGenre method was called with genreName={}", genreName);
    return storytellingGenreDao.createGenre(genreName);
  }

}
