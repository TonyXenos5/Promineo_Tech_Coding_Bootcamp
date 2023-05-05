package com.promineotech.story.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Genre {

  private Long genreId; // Primary Key
  private String genreName; // Unique Key

}
