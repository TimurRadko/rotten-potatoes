package com.epam.web.rotten.potatoes.service.film;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;

import java.util.List;
import java.util.Optional;

/**
 * The interface describes a logic class for operations with Film
 */
public interface FilmService {
    /**
     * Gets a list of films by director column in the films table of using DB.
     *
     * @param director - Film's parameter
     * @return List<Film> - List of Films, where director equals passed into method parameter
     * @throws ServiceException in case of errors
     */
    List<Film> getFilmByDirector(String director) throws ServiceException;

    /**
     * Gets a list of films.
     *
     * @param page         - count of Items per the one web page
     * @param itemsPerPage - item of Film on the one web Page
     * @return List<Film> - List of Films contained in the table 'films'
     * @throws ServiceException in case of errors
     */
    List<Film> getPage(int page, int itemsPerPage) throws ServiceException;

    /**
     * Gets and returns a Film with the specified id.
     * Returns result as Optional of Film found, or empty Optional if found nothing.
     *
     * @param id - passed into the method filmId parameter that is contained in the table 'films'
     * @return Optional<Film> - container that is contained Film
     * @throws ServiceException in case of errors
     */
    Optional<Film> getFilmById(Integer id) throws ServiceException;

    /**
     * Saves a film.
     *
     * @param film - passed into the method Film appropriate Entity
     * @return Optional<Integer> - container that is contained filmId
     * @throws ServiceException in case of errors
     */
    Optional<Integer> save(Film film) throws ServiceException;

    /**
     * Removes a film with the specified id.
     *
     * @param id - passed into the method filmId parameter that is contained in the table 'films'
     * @throws ServiceException in case of errors
     */
    void remove(int id) throws ServiceException;

    /**
     * Calculates and returns the total number of available pages.
     *
     * @param itemPerPage - item of Film on the one web Page
     * @return - return count of pages
     * @throws ServiceException in case of errors
     */
    int getCountRows(int itemPerPage) throws ServiceException;
}
