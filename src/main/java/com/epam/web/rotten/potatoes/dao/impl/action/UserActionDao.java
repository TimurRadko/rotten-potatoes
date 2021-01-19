package com.epam.web.rotten.potatoes.dao.impl.action;

import com.epam.web.rotten.potatoes.dao.Dao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.util.List;

public interface UserActionDao extends Dao<UserAction> {
    /**
     * @param filmId - passed into the method filmId parameter that is contained in the table 'films'
     * @return List<UserAction> - List of UserActions contained in the table 'user-actions'
     * @throws DaoException
     */
    List<UserAction> getReviewsByFilmId(Integer filmId) throws DaoException;

    /**
     * @param userId - passed into the method userId parameter that is contained in the table 'users'
     * @return List<UserAction> - List of UserActions contained in the table 'user-actions'
     * @throws DaoException
     */
    List<UserAction> getReviewsByUserId(Integer userId) throws DaoException;
}
