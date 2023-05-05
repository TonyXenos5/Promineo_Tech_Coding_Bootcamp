DROP TABLE IF EXISTS series_genre;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS series;

CREATE TABLE series (
	series_id INTEGER AUTO_INCREMENT NOT NULL,
	series_name VARCHAR(256) NOT NULL,
	summary TEXT NOT NULL,
	num_of_books INTEGER UNSIGNED NOT NULL,
	PRIMARY KEY (series_id),
	UNIQUE KEY (series_name)
);

CREATE TABLE book (
	book_id INTEGER AUTO_INCREMENT NOT NULL,
	series_name VARCHAR(256) NOT NULL,
	book_title VARCHAR(256) NOT NULL,
	series_book_order INTEGER UNSIGNED NOT NULL,
	author VARCHAR(128) NOT NULL,
	num_of_chapters INTEGER UNSIGNED NOT NULL,
	release_date DATE NOT NULL,
	PRIMARY KEY (book_id),
	FOREIGN KEY (series_name) REFERENCES series (series_name) ON DELETE CASCADE
);

CREATE TABLE genre (
	genre_id INTEGER AUTO_INCREMENT NOT NULL,
	genre_name VARCHAR(128) NOT NULL,
	PRIMARY KEY (genre_id),
	UNIQUE KEY (genre_name)
);

CREATE TABLE series_genre (
	series_id INTEGER AUTO_INCREMENT NOT NULL,
	genre_id INTEGER NOT NULL,
	FOREIGN KEY (series_id) REFERENCES series (series_id) ON DELETE CASCADE,
	FOREIGN KEY (genre_id) REFERENCES genre (genre_id) ON DELETE CASCADE,
	UNIQUE KEY (series_id, genre_id)
);
