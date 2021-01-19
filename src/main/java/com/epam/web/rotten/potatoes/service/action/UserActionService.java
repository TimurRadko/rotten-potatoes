package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.util.List;
import java.util.Optional;

public interface UserActionService {
    /**
     * @param userAction - - passed into the method UserAction appropriate Entity
     * @throws ServiceException
     */
    void addReviewAndRate(UserAction userAction) throws ServiceException;

    /**
     * @param filmId - passed into the method filmId parameter that is contained in the table 'films'
     * @return List<UserAction> - List of UserActions contained in the table 'user-actions'
     * @throws ServiceException
     */
    List<UserAction> findReviewsByFilmId(Integer filmId) throws ServiceException;

    /**
     * @param userId - passed into the method userId parameter that is contained in the table 'users'
     * @return List<UserAction> - List of UserActions contained in the table 'user-actions'
     * @throws ServiceException
     */
    List<UserAction> findReviewsByUserId(Integer userId) throws ServiceException;

    /**
     * @param reviewId - passed into the method reviewId parameter that is contained
     *                 in the table 'user-reviews'
     * @return - Optional<UserAction> - container that is contained UserActions
     * @throws ServiceException
     */
    Optional<UserAction> findReviewById(Integer reviewId) throws ServiceException;

    /**
     * @param id - passed into the method commentId parameter that is contained in the table 'user-reviews'
     * @throws ServiceException
     */
    void remove(int id) throws ServiceException;
}
