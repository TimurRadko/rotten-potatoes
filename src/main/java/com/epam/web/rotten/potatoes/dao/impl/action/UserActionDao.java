package com.epam.web.rotten.potatoes.dao.impl.action;

import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.util.Optional;

public interface UserActionDao {
    Optional<UserAction> addFilmRateAndReview(int rate, String review) throws DaoException;
}
