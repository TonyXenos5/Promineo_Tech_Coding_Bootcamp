package com.promineotech.story.service;

import java.util.List;
import com.promineotech.story.entity.Genre;

public interface StorytellingGenreService {

  List<Genre> fetchGenreInfo(String genreName);

  Genre createGenre(String genreName);

}
