package com.promineotech.story.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeriesRequest {

  @NotNull
  @Length(max = 60)
  @Pattern(regexp = "[\\w\\s]*")
  private String seriesName;

  @NotNull
  private String summary;

  @NotNull
  @Positive
  private int numOfBooks;

}
