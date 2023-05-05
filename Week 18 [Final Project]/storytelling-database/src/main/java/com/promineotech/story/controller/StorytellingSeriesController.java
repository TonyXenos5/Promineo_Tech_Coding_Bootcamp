package com.promineotech.story.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.story.entity.Series;
import com.promineotech.story.entity.SeriesGenre;
import com.promineotech.story.entity.SeriesRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/series")
@OpenAPIDefinition(info = @Info(title = "Storytelling Database: Series"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server.")})
public interface StorytellingSeriesController {
  //@formatter:off
  @Operation(
        summary = "Returns a list of series info",
        description = "Returns a list of series info from the storytelling database",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A list of series info is returned",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Series.class))),
            @ApiResponse(
                responseCode = "400",
                description = "The request parameters are invalid",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(
                responseCode = "404",
                description = "No series were found with the inputted criteria",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(
                responseCode = "500",
                description = "An unplanned error occurred.",
                content = @Content(mediaType = "application/json"))
        },
        parameters  = {
            @Parameter(
                name = "seriesName",
                allowEmptyValue = false,
                required = true,
                description = "The name of a series (i.e. 'Fantasy')")
        }
      )
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Series> fetchSeriesInfo(
      @Length(max = 60)
      @Pattern(regexp = "[\\w\\s]*")
      @RequestParam(required = false)
        String seriesName);
  
  @Operation(
      summary = "Add a series",
      description = "Add a series to the storytelling database",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "A series is added",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = SeriesRequest.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "A required detail about the series was not inputted",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters  = {
          @Parameter(
              name = "seriesName",
              required = true,
              description = "The series' name"),
          @Parameter(
              name = "summary",
              allowEmptyValue = false,
              required = true,
              description = "The summary of the series"),
          @Parameter(
              name = "numOfBooks",
              allowEmptyValue = false,
              required = true,
              description = "The number of books")
      }
    )

@PostMapping
@ResponseStatus(code = HttpStatus.CREATED)
  Series createSeries(@Valid String seriesName, @Valid String summary, @Valid int numOfBooks);
  
  @Operation(
      summary = "Link series and genres",
      description = "Link series and genres in the storytelling database",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "The chosen series and genres are linked",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = SeriesGenre.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "The inputted criteria was not found",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters  = {
          @Parameter(
              name = "seriesName",
              required = true,
              description = "The series' name"),
          @Parameter(
              name = "genreName",
              required = true,
              description = "The genre's name")
      }
    )

@PostMapping("/genre")
@ResponseStatus(code = HttpStatus.CREATED)
  SeriesGenre linkSeriesAndGenre(
      @Length(max = 60)
      @Pattern(regexp = "[\\w\\s]*")
      @RequestParam(required = false)
        String seriesName,
      @Length(max = 60)
      @Pattern(regexp = "[\\w\\s]*")
      @RequestParam(required = false)
        String genreName);
  //@formatter:on
}
