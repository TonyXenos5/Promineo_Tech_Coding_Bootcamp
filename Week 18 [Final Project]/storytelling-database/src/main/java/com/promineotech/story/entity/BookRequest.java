package com.promineotech.story.entity;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookRequest {

  @NotNull
  @Length(max = 60)
  @Pattern(regexp = "[\\w\\s]*")
  private String seriesName;

  @NotNull
  @Length(max = 60)
  @Pattern(regexp = "[\\w\\s]*")
  private String bookTitle;

  @NotNull
  @Positive
  private int seriesBookOrder;

  @NotNull
  @Length(max = 60)
  @Pattern(regexp = "[\\w\\s]*")
  private String author;

  @NotNull
  @Positive
  private int numOfChapters;

  @NotNull
  private Date releaseDate;
}
