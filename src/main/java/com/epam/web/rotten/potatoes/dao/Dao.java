package com.epam.web.rotten.potatoes.dao;

import com.epam.web.rotten.potatoes.exceptions.DaoException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Serializable & Cloneable> {
    Optional<T> getById(Integer id) throws DaoException;
    List<T> getAll() throws DaoException;
    Optional<Integer> save(T item) throws DaoException;
    void remove(int id) throws DaoException;
    int countRows() throws SQLException;
}
