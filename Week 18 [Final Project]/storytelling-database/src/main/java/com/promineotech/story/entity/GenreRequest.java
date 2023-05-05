package com.promineotech.story.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreRequest {

  @NotNull
  @Length(max = 60)
  @Pattern(regexp = "[\\w\\s]*")
  private String genreName;

}
