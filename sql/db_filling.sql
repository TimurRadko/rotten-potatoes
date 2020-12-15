USE `rotten-potatoes`;

SELECT * FROM `films`;

INSERT INTO films(title, director, poster, avg_rate)
values ('The Shawshank Redemption', 'Frank Darabont', 'static/images/shawshenk.jpg',9.3);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Godfather', 'Francis Ford Coppola', 'static/images/godfather.jpg',9.2);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Godfather: Part II', 'Francis Ford Coppola', 'static/images/godfather-2.jpg',9.0);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Dark Knight', 'Christopher Nolan', 'static/images/Dark_Knight.jpg',9.0);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('12 Angry Men', 'Sidney Lumet', 'static/images/12_Angry_Men_(1957_film_poster).jpg', 9.0);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('Schindler''s List', 'Steven Spielberg', 'static/images/shindlers-list.jpg', 8.9);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Lord of the Rings: The Return of the King', 'Peter Jackson', 'static/images/The_Lord_of_the_Rings_-_The_Return_of_the_King_(2003).jpg',8.9);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('Pulp Fiction', 'Quentin Tarantino', 'static/images/Pulp_Fiction_(1994)_poster.jpg',8.9);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Good, the Bad and the Ugly', 'Sergio Leone', 'static/images/good-bad-ugly.jpg',8.8);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Lord of the Rings: The Fellowship of the Ring', 'Peter Jackson', 'static/images/The_Lord_of_the_Rings_The_Fellowship_of_the_Ring_(2001).jpg',8.8);

SELECT * FROM films;

SELECT * FROM users;

INSERT INTO `users`(login, password, rights, rate)
values ('Admin', sha1('Admin'), 'ADMIN', 100);

INSERT INTO `users`(login, password, rights, rate)
values ('Tom', sha1('Tom'), 'USER', 50);

INSERT INTO `users`(login, password, rights, rate)
values ('Jerry', sha1('Jerry'), 'USER', 10);

INSERT INTO `users`(login, password, rights, rate)
values ('Spike', sha1('Spike'), 'USER', 33);

INSERT INTO `users`(login, password, rights, rate)
values ('Casper', sha1('Casper'), 'BLOCKED', 0);

SELECT COUNT(*) FROM films;

SELECT * FROM films WHERE id = 5;

SELECT * FROM films WHERE id=4;