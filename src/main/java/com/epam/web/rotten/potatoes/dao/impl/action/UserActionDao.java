package com.epam.web.rotten.potatoes.dao.impl.action;

import com.epam.web.rotten.potatoes.dao.Dao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.util.List;

public interface UserActionDao extends Dao<UserAction> {
    List<UserAction> getReviewsByFilmId(Integer id) throws DaoException;
    List<UserAction> getReviewsByUserId(Integer id) throws DaoException;
}
