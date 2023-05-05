package com.promineotech.story.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.story.entity.Genre;
import com.promineotech.story.service.StorytellingGenreService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultStorytellingGenreController implements StorytellingGenreController {

  @Autowired
  private StorytellingGenreService storytellingGenreService;

  @Override
  public List<Genre> fetchGenreInfo(String genreName) {
    log.info("genreName={}", genreName);
    return storytellingGenreService.fetchGenreInfo(genreName);
  }

  @Override
  public Genre createGenre(String genreName) {
    log.info("genreName={}", genreName);
    return storytellingGenreService.createGenre(genreName);
  }

}
