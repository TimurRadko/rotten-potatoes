USE `rotten-potatoes`;

SELECT *
FROM `films`;

##INSERT films into DB

INSERT INTO films(title, director, poster, avg_rate)
values ('The Shawshank Redemption', 'Frank Darabont', 'static/images/shawshenk.jpg', 9.3);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Godfather', 'Francis Ford Coppola', 'static/images/godfather.jpg', 9.2);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Godfather: Part II', 'Francis Ford Coppola', 'static/images/godfather-2.jpg', 9.0);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Dark Knight', 'Christopher Nolan', 'static/images/Dark_Knight.jpg', 9.0);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('12 Angry Men', 'Sidney Lumet', 'static/images/12_Angry_Men_(1957_film_poster).jpg', 9.0);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('Schindler''s List', 'Steven Spielberg', 'static/images/shindlers-list.jpg', 8.9);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Lord of the Rings: The Return of the King', 'Peter Jackson',
        'static/images/The_Lord_of_the_Rings_-_The_Return_of_the_King_(2003).jpg', 8.9);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('Pulp Fiction', 'Quentin Tarantino', 'static/images/Pulp_Fiction_(1994)_poster.jpg', 8.9);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Good, the Bad and the Ugly', 'Sergio Leone', 'static/images/good-bad-ugly.jpg', 8.8);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Lord of the Rings: The Fellowship of the Ring', 'Peter Jackson',
        'static/images/The_Lord_of_the_Rings_The_Fellowship_of_the_Ring_(2001).jpg', 8.8);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('Fight Club', 'David Fincher', 'static/images/Fight club.jpg', 8.8);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('Forrest Gump', 'Robert Zemeckis', 'static/images/Forrest_Gump_poster.jpg', 8.8);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('Inception', 'Christopher Nolan', 'static/images/Inception.jpg', 8.8);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Lord of the Rings: The Two Towers', 'Peter Jackson',
        'static/images/Lord_of_the_Rings_-_The_Two_Towers_(2002).jpg', 8.7);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('Star Wars: Episode V - The Empire Strikes Back', 'Irvin Kershner',
        'static/images/SW_-_Empire_Strikes_Back.jpg', 8.7);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('The Matrix', 'Lana Wachowski and Lilly Wachowski', 'static/images/The_Matrix_Poster.jpg', 8.7);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('Goodfellas', 'Martin Scorsese', 'static/images/Goodfellas.jpg', 8.7);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('One Flew Over the Cuckoo''s Nest', 'Milos Forman', 'static/images/One_Flew_Over_the_Cuckoo''s_Nest_poster.jpg',
        8.7);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('Seven Samurai', 'Akira Kurosawa', 'static/images/SevenSamurai.jpg', 8.6);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('Seven', 'David Fincher', 'static/images/Seven.jpg', 8.6);

INSERT INTO `films`(title, director, poster, avg_rate)
values ('Life Is Beautiful', 'Roberto Benigni', 'static/images/Vitaebella.jpg', 8.6);


SELECT *
FROM films;

SELECT *
FROM users;

##INSERT users into DB

INSERT INTO `users`(login, password, rights, rate, blocked)
values ('Admin', sha1('Admin'), 'ADMIN', 100, false);

INSERT INTO `users`(login, password, rights, rate, blocked)
values ('Tom', sha1('Tom'), 'USER', 50, false);

INSERT INTO `users`(login, password, rights, rate, blocked)
values ('Jerry', sha1('Jerry'), 'USER', 10, false);

INSERT INTO `users`(login, password, rights, rate, blocked)
values ('Spike', sha1('Spike'), 'USER', 33, false);

INSERT INTO `users`(login, password, rights, rate, blocked)
values ('Casper', sha1('Casper'), 'USER', 0, true);

SELECT COUNT(*)
FROM films;

SELECT *
FROM films
WHERE id = 10;

SELECT *
FROM films
WHERE id = 4;

SELECT *
FROM user_actions;

SELECT COUNT(*)
FROM user_actions
WHERE user_id = 2;

SELECT film_id
FROM user_actions
where user_id = 2;

SELECT * FROM user_actions WHERE film_id=1;
