package com.promineotech.story.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.story.entity.Series;
import com.promineotech.story.entity.SeriesGenre;
import com.promineotech.story.service.StorytellingSeriesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultStorytellingSeriesController implements StorytellingSeriesController {

  @Autowired
  private StorytellingSeriesService storytellingSeriesService;

  @Override
  public List<Series> fetchSeriesInfo(String seriesName) {
    log.info("seriesName={}", seriesName);
    return storytellingSeriesService.fetchSeriesInfo(seriesName);
  }

  @Override
  public Series createSeries(String seriesName, String summary, int numOfBooks) {
    log.info("seriesName={}, summary={}, numOfBooks={}", seriesName, summary, numOfBooks);
    return storytellingSeriesService.createSeries(seriesName, summary, numOfBooks);
  }

  @Override
  public SeriesGenre linkSeriesAndGenre(String seriesName, String genreName) {
    log.info("seriesName={}, genreName={}", seriesName, genreName);
    return storytellingSeriesService.linkSeriesAndGenre(seriesName, genreName);
  }

}
