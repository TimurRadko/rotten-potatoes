package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.util.List;

public interface UserActionService {
    void addReviewAndRate(UserAction userAction) throws ServiceException;
    List<UserAction> findAllReviewsByFilmId(int filmId) throws ServiceException;
}
