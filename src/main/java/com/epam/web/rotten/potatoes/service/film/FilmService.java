package com.epam.web.rotten.potatoes.service.film;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<Film> getFilmByDirector(String director) throws ServiceException;
    List<Film> getPage(int page, int itemsPerPage) throws ServiceException;
    Optional<Film> getFilmById(Integer id) throws ServiceException;
    Optional<Integer> save(Film film) throws ServiceException;
    void remove(int id) throws ServiceException;
    int getCountRows(int itemPerPage) throws ServiceException;
}
