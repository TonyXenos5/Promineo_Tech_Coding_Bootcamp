package com.promineotech.story.entity;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeriesGenre {

  @NotNull
  private Long seriesId;

  @NotNull
  private Long genreId;
}
