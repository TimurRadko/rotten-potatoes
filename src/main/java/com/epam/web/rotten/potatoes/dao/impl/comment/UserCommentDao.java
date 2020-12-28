package com.epam.web.rotten.potatoes.dao.impl.comment;

import com.epam.web.rotten.potatoes.dao.Dao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.UserComment;

import java.util.List;

public interface UserCommentDao extends Dao<UserComment> {
    List<UserComment> getCommentsByFilmId(Integer id) throws DaoException;
    List<UserComment> getCommentsByUserId(Integer id) throws DaoException;
}
