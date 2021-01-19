package com.epam.web.rotten.potatoes.dao;

import com.epam.web.rotten.potatoes.exceptions.DaoException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Serializable & Cloneable> {
    /**
     * @param id - passed into the method id parameter that is contained in one of all tables in the DB
     * @return Optional<T> - container that is contained Entity (a typed parameter)
     * @throws DaoException
     */
    Optional<T> getById(Integer id) throws DaoException;

    /**
     * @return List<T> - List of T contained in one of all tables in the DB
     * @throws DaoException
     */
    List<T> getAll() throws DaoException;

    /**
     * @param item - T one of all Entity in DB
     * @return Optional<Integer> - container that is contained Entity Id
     * @throws DaoException
     */
    Optional<Integer> save(T item) throws DaoException;

    /**
     * @param id - passed into the method Entity Id parameter that is contained in one of all tables in the DB
     * @throws DaoException
     */
    void remove(int id) throws DaoException;

    /**
     * @return int - count of all Rows in the table
     * @throws SQLException
     */
    int countRows() throws SQLException;
}
