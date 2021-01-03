package com.epam.web.rotten.potatoes.service.comment;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserComment;

import java.util.List;
import java.util.Optional;

public interface UserCommentService {
    void addComment(UserComment userComment) throws ServiceException;
    List<UserComment> findCommentsByFilmId(Integer filmId) throws ServiceException;
    List<UserComment> findCommentsByUserId(Integer userId) throws ServiceException;
    Optional<UserComment> findCommentById(Integer commentId) throws ServiceException;
    void remove(int id) throws ServiceException;
}
