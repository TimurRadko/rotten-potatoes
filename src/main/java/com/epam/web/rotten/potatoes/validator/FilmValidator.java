package com.epam.web.rotten.potatoes.validator;

import com.epam.web.rotten.potatoes.model.Film;

public class FilmValidator implements Validator<Film> {
    private static final int MAX_LENGTH = 100;

    @Override
    public boolean isValid(Film film) {
        if ((film.getTitle() == null) || (film.getTitle().isEmpty()) || (film.getTitle().length() > MAX_LENGTH)) {
            return false;
        }

        if ((film.getDirector() == null) || (film.getDirector().isEmpty()) || (film.getDirector().length() > MAX_LENGTH)) {
            return false;
        }

        if ((film.getPoster() == null) || (film.getPoster().isEmpty()) || (film.getPoster().length() > 500)) {
            return false;
        }

        if ((film.getDefaultRate() < 0) || (film.getDefaultRate() > 10)) {
            return false;
        }
        return true;
    }
}
