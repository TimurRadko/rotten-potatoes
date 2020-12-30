package com.epam.web.rotten.potatoes.service.film;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<Film> getAll() throws ServiceException;
    List<Film> getFilmByDirector(String director) throws ServiceException;
    List<Film> getFilmsPart(int startsWith, int endsWith) throws ServiceException;
    Optional<Film> getFilmById(Integer id) throws ServiceException;
    Optional<Integer> saveFilm(Film film) throws ServiceException;
}
