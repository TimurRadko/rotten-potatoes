package com.epam.web.rotten.potatoes.dao.impl.film;

import com.epam.web.rotten.potatoes.dao.Dao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmDao extends Dao<Film> {
    /**
     * @param director - Film's parameter
     * @return List<Film> - List of Films, where director equals passed into method parameter
     * @throws DaoException
     */
    List<Film> findFilmByDirector(String director) throws DaoException;

    /**
     * @param amount - count of Items per the one web page
     * @param from   - count of current page
     * @return List<Film> - List of Films contained in the table 'films'
     * @throws DaoException
     */
    List<Film> findFilmsPartList(int amount, int from) throws DaoException;
}
