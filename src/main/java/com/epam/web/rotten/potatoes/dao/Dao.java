package com.epam.web.rotten.potatoes.dao;

import com.epam.web.rotten.potatoes.exceptions.DaoException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Serializable & Cloneable> {
    /**
     * Finds a Entity with the specified id.
     *
     * @param id - passed into the method id parameter that is contained in one of all tables in the DB
     * @return Optional<T> - container that is contained Entity (a typed parameter)
     * @throws DaoException in case of errors
     */
    Optional<T> findById(Integer id) throws DaoException;

    /**
     * Gets a list of Entities.
     *
     * @return List<T> - List of T contained in one of all tables in the DB
     * @throws DaoException in case of errors
     */
    List<T> findAll() throws DaoException;

    /**
     * Saves a Entity.
     *
     * @param item - T one of all Entity in DB
     * @return Optional<Integer> - container that is contained Entity Id
     * @throws DaoException in case of errors
     */
    Optional<Integer> save(T item) throws DaoException;

    /**
     * Removes a Entity with the specified id.
     *
     * @param id - passed into the method Entity Id parameter that is contained in one of all tables in the DB
     * @throws DaoException in case of errors
     */
    void remove(int id) throws DaoException;

    /**
     * Calculates and returns the total number of available pages.
     *
     * @return int - count of all Rows in the table
     * @throws SQLException in case of errors
     */
    int countRows() throws SQLException;
}
