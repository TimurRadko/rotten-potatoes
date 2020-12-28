package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.util.List;
import java.util.Optional;

public interface UserActionService {
    void addReviewAndRate(UserAction userAction) throws ServiceException;
    List<UserAction> findReviewsByFilmId(Integer filmId) throws ServiceException;
    List<UserAction> findReviewsByUserId(Integer userId) throws ServiceException;
}
