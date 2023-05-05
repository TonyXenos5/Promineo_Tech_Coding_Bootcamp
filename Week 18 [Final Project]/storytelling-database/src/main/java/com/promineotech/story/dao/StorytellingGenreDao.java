package com.promineotech.story.dao;

import java.util.List;
import com.promineotech.story.entity.Genre;

public interface StorytellingGenreDao {

  List<Genre> fetchGenreInfo(String genreName);

  Genre createGenre(String genreName);

}
