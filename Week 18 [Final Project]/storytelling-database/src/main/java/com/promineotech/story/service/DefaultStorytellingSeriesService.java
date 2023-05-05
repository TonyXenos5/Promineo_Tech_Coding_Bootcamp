package com.promineotech.story.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.story.dao.StorytellingSeriesDao;
import com.promineotech.story.entity.Series;
import com.promineotech.story.entity.SeriesGenre;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultStorytellingSeriesService implements StorytellingSeriesService {

  @Autowired
  private StorytellingSeriesDao storytellingSeriesDao;

  @Transactional(readOnly = true)
  @Override
  public List<Series> fetchSeriesInfo(String seriesName) {
    log.info("The fetchSeriesInfo method was called with seriesName={}", seriesName);
    List<Series> series = storytellingSeriesDao.fetchSeriesInfo(seriesName);

    if (series.isEmpty()) {
      String msg = String.format("No series found with seriesName=%s", seriesName);

      throw new NoSuchElementException(msg);
    }

    return series;

  }

  @Transactional
  @Override
  public Series createSeries(String seriesName, String summary, int numOfBooks) {
    log.info("The createSeries method was called with seriesName={}, summary={}, numOfBooks={}",
        seriesName, summary, numOfBooks);
    return storytellingSeriesDao.createSeries(seriesName, summary, numOfBooks);
  }

  @Transactional
  @Override
  public SeriesGenre linkSeriesAndGenre(String seriesName, String genreName) {
    log.info("The linkSeriesAndGenre method was called with seriesName={}, genreName={}",
        seriesName, genreName);
    return storytellingSeriesDao.linkSeriesAndGenre(seriesName, genreName);
  }

}
