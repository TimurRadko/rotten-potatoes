package com.epam.web.rotten.potatoes.service.comment;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserComment;

import java.util.List;
import java.util.Optional;

public interface UserCommentService {
    /**
     * @param userComment - passed into the method UserComment appropriate Entity
     * @throws ServiceException
     */
    void addComment(UserComment userComment) throws ServiceException;

    /**
     * @param filmId - passed into the method filmId parameter that is contained in the table 'films'
     * @return List<UserComment> - UserComment's List contained in the DB
     * @throws ServiceException
     */
    List<UserComment> findCommentsByFilmId(Integer filmId) throws ServiceException;

    /**
     * @param userId - passed into the method userId parameter that is contained in the table 'users'
     * @return List<UserComment> - UserComment's List contained in the DB
     * @throws ServiceException
     */
    List<UserComment> findCommentsByUserId(Integer userId) throws ServiceException;

    /**
     * @param commentId - passed into the method commentId parameter that is contained
     *                  in the table 'user-comments'
     * @return - Optional<UserComment> - container that is contained UserComments
     * @throws ServiceException
     */
    Optional<UserComment> findCommentById(Integer commentId) throws ServiceException;

    /**
     * @param id - passed into the method commentId parameter that is contained in the table 'user-comments'
     * @throws ServiceException
     */
    void remove(int id) throws ServiceException;
}
