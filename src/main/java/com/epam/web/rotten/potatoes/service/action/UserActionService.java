package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.util.List;
import java.util.Optional;

/**
 * The interface describes a logic class for operations with UserActions
 */
public interface UserActionService {
    /**
     * Adds a new review and rate.
     *
     * @param userAction - - passed into the method UserAction appropriate Entity
     * @throws ServiceException in case of errors
     */
    void addReviewAndRate(UserAction userAction) throws ServiceException;

    /**
     * Gets a list of userReviews by filmId id in the table films.
     *
     * @param filmId - passed into the method filmId parameter that is contained in the table 'films'
     * @return List<UserAction> - List of UserActions contained in the table 'user-actions'
     * @throws ServiceException in case of errors
     */
    List<UserAction> getReviewsByFilmId(Integer filmId) throws ServiceException;

    /**
     * Gets a list of userReviews by userId column id in the table users.
     *
     * @param userId - passed into the method userId parameter that is contained in the table 'users'
     * @return List<UserAction> - List of UserActions contained in the table 'user-actions'
     * @throws ServiceException in case of errors
     */
    List<UserAction> getReviewsByUserId(Integer userId) throws ServiceException;

    /**
     * Gets and returns a UserReview with the specified id.
     * Returns result as Optional of UserReview found, or empty Optional if found nothing.
     *
     * @param reviewId - passed into the method reviewId parameter that is contained
     *                 in the table 'user-reviews'
     * @return - Optional<UserAction> - container that is contained UserActions
     * @throws ServiceException in case of errors
     */
    Optional<UserAction> getReviewById(Integer reviewId) throws ServiceException;

    /**
     * Removes a userReview with the specified id.
     *
     * @param id - passed into the method commentId parameter that is contained in the table 'user-reviews'
     * @throws ServiceException in case of errors
     */
    void remove(int id) throws ServiceException;
}