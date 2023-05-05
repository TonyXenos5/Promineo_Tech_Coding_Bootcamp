package com.promineotech.story.service;

import java.util.List;
import com.promineotech.story.entity.Series;
import com.promineotech.story.entity.SeriesGenre;

public interface StorytellingSeriesService {

  List<Series> fetchSeriesInfo(String seriesName);

  Series createSeries(String seriesName, String summary, int numOfBooks);

  SeriesGenre linkSeriesAndGenre(String seriesName, String genreName);

}
