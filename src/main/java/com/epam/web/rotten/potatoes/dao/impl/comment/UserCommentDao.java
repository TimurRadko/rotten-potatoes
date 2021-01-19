package com.epam.web.rotten.potatoes.dao.impl.comment;

import com.epam.web.rotten.potatoes.dao.Dao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.UserComment;

import java.util.List;

public interface UserCommentDao extends Dao<UserComment> {
    /**
     * @param filmId - passed into the method filmId parameter that is contained in the table 'films'
     * @return List<UserComment> - UserComment's List contained in the DB
     * @throws DaoException
     */
    List<UserComment> getCommentsByFilmId(Integer filmId) throws DaoException;

    /**
     * @param userId - passed into the method userId parameter that is contained in the table 'users'
     * @return List<UserComment> - UserComment's List contained in the DB
     * @throws DaoException
     */
    List<UserComment> getCommentsByUserId(Integer userId) throws DaoException;
}
