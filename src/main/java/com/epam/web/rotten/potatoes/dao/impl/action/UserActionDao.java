package com.epam.web.rotten.potatoes.dao.impl.action;

import com.epam.web.rotten.potatoes.dao.Dao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.util.List;

public interface UserActionDao extends Dao<UserAction> {
   // int saveReviewAndRate(int filmRate, String review, int userId, int filmId) throws DaoException;
    List<Film> getCommentsByFilmsId(int id);
}
