-- Series
INSERT INTO series (series_name, summary, num_of_books) VALUES ('Throne of Glass', 'Throne of Glass follows the journey of Celaena Sardothien, a teenage assassin in a corrupt kingdom with a tyrannical ruler, the King of Adarlan.', 8);
INSERT INTO series (series_name, summary, num_of_books) VALUES ('The Maze Runner', 'The Maze Runner series center on Thomas, a 16-year-old boy with no memory of life before the Maze, the friends he makes in the Glade, and their struggles to survive a series of deadly tests and overthrow the corporation running them.', 5);
INSERT INTO series (series_name, summary, num_of_books) VALUES ('The Cruel Stars', 'In this epic sci-fi adventure for fans of The Expanse and Battlestar Galactica, five intrepid heroes must unite to save civilization after a long-dormant enemy awakens and strikes a devastating blow', 2);
INSERT INTO series (series_name, summary, num_of_books) VALUES ('Computer Programming Languages for Beginners', 'Computer programming is one of the top sought-after skills in today’s ever-evolving society. Jump on the bandwagon before it’s too late…', 1);

-- Book [Throne of Glass]
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('Throne of Glass', 'Throne of Glass', 1, 'Sarah J. Maas', 55, '2012-08-02');
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('Throne of Glass', 'Crown of Midnight', 2, 'Sarah J. Maas', 56, '2013-08-27');
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('Throne of Glass', 'Heir of Fire', 3, 'Sarah J. Maas', 68, '2014-09-02');
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('Throne of Glass', 'The Assassin\'\s Blade', 4, 'Sarah J. Maas', 54, '2014-03-13');
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('Throne of Glass', 'Queen of Shadows', 5, 'Sarah J. Maas', 89, '2015-09-01');
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('Throne of Glass', 'Empire of Storms', 6, 'Sarah J. Maas', 75, '2016-09-06');
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('Throne of Glass', 'Tower of Dawn', 7, 'Sarah J. Maas', 68, '2017-09-05');
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('Throne of Glass', 'Kingdom of Ash', 8, 'Sarah J. Maas', 122, '2018-10-23');

-- Book [The Maze Runner]
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('The Maze Runner', 'The Maze Runner', 1, 'James Dashner', 62, '2009-10-06');
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('The Maze Runner', 'The Scorch Trials', 2, 'James Dashner', 65, '2010-09-18');
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('The Maze Runner', 'The Death Cure', 3, 'James Dashner', 73, '2011-10-11');
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('The Maze Runner', 'The Kill Order', 4, 'James Dashner', 69, '2012-08-14');
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('The Maze Runner', 'The Fever Code', 5, 'James Dashner', 64, '2016-09-27');

-- Book [The Cruel Stars]
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('The Cruel Stars', 'The Cruel Stars', 1, 'John Birmingham', 42, '2019-08-20');
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('The Cruel Stars', 'The Shattered Skies', 2, 'John Birmingham', 37, '2022-01-11');

-- Book [Computer Programming Languages for Beginners]
INSERT INTO book (series_name, book_title, series_book_order, author, num_of_chapters, release_date) VALUES ('Computer Programming Languages for Beginners', 'Computer Programming Languages for Beginners: A Complete Breakdown of Java, SQL, C++, HTML, and Python', 1, 'Adesh Silva', 8, '2019-09-24');

-- Genre
INSERT INTO genre (genre_name) VALUES ('Fantasy');
INSERT INTO genre (genre_name) VALUES ('Science Fiction');
INSERT INTO genre (genre_name) VALUES ('Dystopian');
INSERT INTO genre (genre_name) VALUES ('Adventure');
INSERT INTO genre (genre_name) VALUES ('Nonfiction'); 
INSERT INTO genre (genre_name) VALUES ('Romance');
INSERT INTO genre (genre_name) VALUES ('Detective & Mystery');
INSERT INTO genre (genre_name) VALUES ('Horror');
INSERT INTO genre (genre_name) VALUES ('Thriller');

-- Series-Genre
INSERT INTO series_genre (series_id, genre_id) VALUES (1, 1);
INSERT INTO series_genre (series_id, genre_id) VALUES (2, 3);
INSERT INTO series_genre (series_id, genre_id) VALUES (3, 2);
INSERT INTO series_genre (series_id, genre_id) VALUES (4, 5);