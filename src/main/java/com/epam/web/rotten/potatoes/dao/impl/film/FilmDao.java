package com.epam.web.rotten.potatoes.dao.impl.film;

import com.epam.web.rotten.potatoes.dao.Dao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.Film;

import java.util.List;

public interface FilmDao extends Dao<Film> {
    /**
     * Finds a a list of Film by director column in the films table of using DB.
     *
     * @param director - Film's parameter
     * @return List<Film> - List of Films, where director equals passed into method parameter
     * @throws DaoException in case of errors
     */
    List<Film> findFilmByDirector(String director) throws DaoException;

    /**
     * Finds a list of Film.
     *
     * @param amount - count of Items per the one web page
     * @param from   - count of current page
     * @return List<Film> - List of Films contained in the table 'films'
     * @throws DaoException in case of errors
     */
    List<Film> findFilmsPartList(int amount, int from) throws DaoException;
}
