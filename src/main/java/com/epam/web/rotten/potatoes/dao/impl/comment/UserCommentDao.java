package com.epam.web.rotten.potatoes.dao.impl.comment;

import com.epam.web.rotten.potatoes.dao.Dao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.UserComment;

import java.util.List;

/**
 * Extending DAO interface for managing UserComments
 */
public interface UserCommentDao extends Dao<UserComment> {
    /**
     * Finds a a list of UserComment by filmId column id in the table films.
     *
     * @param filmId - passed into the method filmId parameter that is contained in the table 'films'
     * @return List<UserComment> - UserComment's List contained in the DB
     * @throws DaoException in case of errors
     */
    List<UserComment> findCommentsByFilmId(Integer filmId) throws DaoException;

    /**
     * Finds a a list of UserComment by userId column id in the table users.
     *
     * @param userId - passed into the method userId parameter that is contained in the table 'users'
     * @return List<UserComment> - UserComment's List contained in the DB
     * @throws DaoException in case of errors
     */
    List<UserComment> findCommentsByUserId(Integer userId) throws DaoException;
}