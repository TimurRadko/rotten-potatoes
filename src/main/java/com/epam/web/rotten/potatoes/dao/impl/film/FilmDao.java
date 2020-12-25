package com.epam.web.rotten.potatoes.dao.impl.film;

import com.epam.web.rotten.potatoes.dao.Dao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.Film;

import java.util.List;

public interface FilmDao extends Dao<Film> {
    List<Film> sortByRow(String rowName) throws DaoException;
    List<Film> getFilmByDirector(String director) throws DaoException;
    List<Film> findFilmsPartList(int startsWith, int endsWith) throws DaoException;
}
