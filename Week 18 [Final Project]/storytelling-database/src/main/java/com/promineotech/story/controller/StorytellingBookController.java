package com.promineotech.story.controller;

import java.sql.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.story.entity.Book;
import com.promineotech.story.entity.BookRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/book")
@OpenAPIDefinition(info = @Info(title = "Storytelling Database: Book"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server.")})
public interface StorytellingBookController {
  //@formatter:off
  @Operation(
        summary = "Returns a list of books",
        description = "Returns a list of books given an series name",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A list of books is returned",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Book.class))),
            @ApiResponse(
                responseCode = "400",
                description = "The request parameters are invalid",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(
                responseCode = "404",
                description = "No books were found in the series name",
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
                description = "The series name (i.e. 'Throne of Glass')")
        }
      )
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Book> fetchBooks(
      @Length(max = 60)
      @Pattern(regexp = "[\\w\\s]*")
      @RequestParam(required = true)
        String seriesName);
  
  @Operation(
      summary = "Add a book",
      description = "Add a book to the storytelling database",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "A book is added",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BookRequest.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "A required detail about the book was not inputted",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters  = {
          @Parameter(
              name = "bookTitle",
              required = true,
              description = "The book title"),
          @Parameter(
              name = "seriesName",
              required = true,
              description = "The series' name"),
          @Parameter(
              name = "seriesBookOrder",
              required = true,
              description = "The book's order in a series"),
          @Parameter(
              name = "author",
              required = true,
              description = "The author"),
          @Parameter(
              name = "numOfChapters",
              required = true,
              description = "The number of chapters"),
          @Parameter(
              name = "releaseDate",
              required = true,
              description = "The release date")
      }
    )

@PostMapping
@ResponseStatus(code = HttpStatus.CREATED)
  Book createBook(
      @Valid String bookTitle, 
      @Valid String seriesName, 
      @Valid int seriesBookOrder, 
      @Valid String author, 
      @Valid int numOfChapters, 
      @Valid Date releaseDate);

  @Operation(
      summary = "Update a book's order in a series",
      description = "Update a book's order in the series with the addition " 
      + "of new entries taking place before or after an established book in the database",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The book's order in the series has been updated",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Book.class))),
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
              name = "bookTitle",
              allowEmptyValue = false,
              required = true,
              description = "The book title"),
          @Parameter(
              name = "seriesBookOrder",
              allowEmptyValue = false,
              required = true,
              description = "The book's order in a series")
      }
    )
  
@PutMapping
@ResponseStatus(code = HttpStatus.OK)
  Book updateSeriesBookOrder(String bookTitle, int seriesBookOrder);

  @Operation(
      summary = "Delete a book",
      description = "Delete a book from the storytelling database",
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "A book is deleted",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Book.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No book with the inputted criteria was found",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters  = {
          @Parameter(
              name = "bookTitle",
              allowEmptyValue = false,
              required = true,
              description = "The book title")
      }
    )
  
@DeleteMapping
@ResponseStatus(code = HttpStatus.NO_CONTENT)
  Book deleteBook(String bookTitle);
  //@formatter:on
}
