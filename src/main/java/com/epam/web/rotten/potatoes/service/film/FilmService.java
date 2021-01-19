package com.epam.web.rotten.potatoes.service.film;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    /**
     *
     * @param director - Film's parameter
     * @return List<Film> - List of Films, where director equals passed into method parameter
     * @throws ServiceException
     */
    List<Film> getFilmByDirector(String director) throws ServiceException;

    /**
     *
     * @param page - count of Items per the one web page
     * @param itemsPerPage - item of Film on the one web Page
     * @return List<Film> - List of Films contained in the table 'films'
     * @throws ServiceException
     */
    List<Film> getPage(int page, int itemsPerPage) throws ServiceException;

    /**
     *
     * @param id - passed into the method filmId parameter that is contained in the table 'films'
     * @return Optional<Film> - container that is contained Film
     * @throws ServiceException
     */
    Optional<Film> getFilmById(Integer id) throws ServiceException;

    /**
     *
     * @param film - passed into the method Film appropriate Entity
     * @return Optional<Integer> - container that is contained filmId
     * @throws ServiceException
     */
    Optional<Integer> save(Film film) throws ServiceException;

    /**
     *
     * @param id - passed into the method filmId parameter that is contained in the table 'films'
     * @throws ServiceException
     */
    void remove(int id) throws ServiceException;

    /**
     *
     * @param itemPerPage - item of Film on the one web Page
     * @return - return count of pages
     * @throws ServiceException
     */
    int getCountRows(int itemPerPage) throws ServiceException;
}
