package com.epam.web.rotten.potatoes.dao;

import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {
    Optional<T> getById(Integer id) throws DaoException;
    List<T> getAll() throws DaoException;
    int save(T item) throws DaoException;
    void remove(int id) throws DaoException;
}
