# SpringFinalProject
Final Project for Spring and Promineo Tech Back-End Coding Bootcamp

A Storytelling Database Web API

--------------------------------------------------------

CRUD Operations

Book [GET List of books in a series, POST New book, PUT Updated series' book order, DELETE Book]

Series [GET List of series information, POST New series, POST Series-Genre entry]

Genre [GET List of genre information, POST New genre]

--------------------------------------------------------

Logging document for alterations made to the final project from the initial proposal

* Changed the One to Many relationship from Series_Id -> Book to Series_Name -> Book [Change made to account for accuracy and simplicity in linking a book with a series, as previous relationship requires user to automatically know the primary key value of a series in an updating database]

* Added Unique Key identifier to Series_Name [Ensures the One to Many relationship links to the proper series for the Book table, and prevents the issue in POST method for Book for trying to link to a non-existing series]

* Removed Unique Key identifier from Series_Book_Order [Ran into issue of having duplicate entries for book order, which defeats the purpose of this column]

* Removed GET method for Series: Browse Series by Genre

* Added NOT NULL to Summary

* Added UNSIGNED to num_of_books, series_book_order, num_of_chapters

* Changed data type and name of release_year to DATE and release_date (and subsequently updated the appropriate field in the initial data sql file)

* Added Unique Key identifier to genre_name to ensure that duplicate genres don't occur

* Changed the GET method purpose for Genre and Series (No longer returns a list of all genres/series, but instead showcases information about a selected genre/series)
