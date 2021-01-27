package com.epam.web.rotten.potatoes.service.comment;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserComment;

import java.util.List;
import java.util.Optional;

/**
 * The interface describes a logic class for operations with UserComments
 */
public interface UserCommentService {
    /**
     * Adds a new comment.
     *
     * @param userComment - passed into the method UserComment appropriate Entity
     * @throws ServiceException in case of errors
     */
    void addComment(UserComment userComment) throws ServiceException;

    /**
     * Gets a list of userComments by filmId id in the table films.
     *
     * @param filmId - passed into the method filmId parameter that is contained in the table 'films'
     * @return List<UserComment> - UserComment's List contained in the DB
     * @throws ServiceException in case of errors
     */
    List<UserComment> getCommentsByFilmId(Integer filmId) throws ServiceException;

    /**
     * Gets a list of userComments by userId id in the table users..
     *
     * @param userId - passed into the method userId parameter that is contained in the table 'users'
     * @return List<UserComment> - UserComment's List contained in the DB
     * @throws ServiceException in case of errors
     */
    List<UserComment> getCommentsByUserId(Integer userId) throws ServiceException;

    /**
     * Gets and returns a UserComment with the specified id.
     * Returns result as Optional of UserComment found, or empty Optional if found nothing.
     *
     * @param commentId - passed into the method commentId parameter that is contained
     *                  in the table 'user-comments'
     * @return - Optional<UserComment> - container that is contained UserComments
     * @throws ServiceException in case of errors
     */
    Optional<UserComment> getCommentById(Integer commentId) throws ServiceException;

    /**
     * Removes a userComment with the specified id.
     *
     * @param id - passed into the method commentId parameter that is contained in the table 'user-comments'
     * @throws ServiceException in case of errors
     */
    void remove(int id) throws ServiceException;
}