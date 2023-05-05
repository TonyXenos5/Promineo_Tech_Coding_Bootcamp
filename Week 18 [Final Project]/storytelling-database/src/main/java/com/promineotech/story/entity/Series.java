package com.promineotech.story.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Series {

  private Long seriesId; // Primary Key
  private String seriesName; // Unique Key
  private String summary;
  private int numOfBooks;

}
