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
import com.promineotech.story.entity.Genre;
import com.promineotech.story.entity.GenreRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/genre")
@OpenAPIDefinition(info = @Info(title = "Storytelling Database: Genre"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server.")})
public interface StorytellingGenreController {
  //@formatter:off
  @Operation(
        summary = "Returns a list of genre info",
        description = "Returns a list of genre info from the storytelling database",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A list of genre info is returned",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Genre.class))),
            @ApiResponse(
                responseCode = "400",
                description = "The request parameters are invalid",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(
                responseCode = "404",
                description = "No genres were found with the inputted criteria",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(
                responseCode = "500",
                description = "An unplanned error occurred.",
                content = @Content(mediaType = "application/json"))
        },
        parameters  = {
            @Parameter(
                name = "genreName",
                allowEmptyValue = false,
                required = true,
                description = "The name of a genre (i.e. 'Fantasy')")
        }
      )
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Genre> fetchGenreInfo(
      @Length(max = 60)
      @Pattern(regexp = "[\\w\\s]*")
      @RequestParam(required = false)
        String genreName);
  
  @Operation(
      summary = "Add a genre",
      description = "Add a genre to the storytelling database",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "A genre is added",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = GenreRequest.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "A required detail about the genre was not inputted",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters  = {
          @Parameter(
              name = "genreName",
              required = true,
              description = "The genre name")
      }
    )

@PostMapping
@ResponseStatus(code = HttpStatus.CREATED)
  Genre createGenre(@Valid String genreName);
  //@formatter:on
}
