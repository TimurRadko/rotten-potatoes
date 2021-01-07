DROP SCHEMA `rotten-potatoes`;

CREATE SCHEMA IF NOT EXISTS `rotten-potatoes` DEFAULT CHARACTER SET utf8;
USE `rotten-potatoes`;

CREATE TABLE IF NOT EXISTS `users`
(
    `id`       INT                             NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(50)                     NOT NULL,
    `password` VARCHAR(50)                     NOT NULL,
    `rights`   ENUM('user', 'admin')           NOT NULL DEFAULT 'user',
    `rate`     INT                             NOT NULL,
    `blocked`  BOOLEAN                         NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `films`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `title`    VARCHAR(100) NOT NULL,
    `director` VARCHAR(45)  NOT NULL,
    `poster`   VARCHAR(500) NULL,
    `default_rate` DOUBLE NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `user_actions`
(
    `id`        INT           NOT NULL AUTO_INCREMENT,
    `film_rate` INT           NULL,
    `review`    VARCHAR(1000) NULL,
    `user_id`   INT           NOT NULL,
    `film_id`   INT           NOT NULL,
    PRIMARY KEY (`id`, `user_id`, `film_id`),
    INDEX `fk_user_estimates_users_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_user_estimates_films_idx` (`film_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_estimates_users`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_user_estimates_films`
        FOREIGN KEY (`film_id`)
            REFERENCES `films` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `user_comments`
(
    `id`      INT          NOT NULL AUTO_INCREMENT,
    `comment` VARCHAR(300) NULL,
    `film_id` INT          NOT NULL,
    `user_id` INT          NOT NULL,
    PRIMARY KEY (`id`, `film_id`, `user_id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_user_comments_films_idx` (`film_id` ASC) VISIBLE,
    INDEX `fk_user_comments_users_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_comments_films`
        FOREIGN KEY (`film_id`)
            REFERENCES `films` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_user_comments_users`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);