package com.promineotech.story.entity;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

  private Long bookId; // Primary Key
  private String seriesName; // Foreign Key from Series
  private String bookTitle;
  private int seriesBookOrder;
  private String author;
  private int numOfChapters;
  private Date releaseDate;

}
