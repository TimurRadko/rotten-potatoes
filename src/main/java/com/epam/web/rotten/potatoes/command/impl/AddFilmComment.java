package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.UserComment;
import com.epam.web.rotten.potatoes.service.comment.UserCommentServiceImpl;

public class AddFilmComment implements Command {
    private final UserCommentServiceImpl userCommentService;

    private static final String COMMENT_PARAMETER = "comment";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_EMPTY_COMMENT = "errorEmptyComment";
    private static final String COMMENT_PAGE = "WEB-INF/views/comment.jsp";
    private static final String USER_ID_PARAMETER = "user_id";
    private static final String FILM = "film";
    private static final String GO_TO_FILM_HOME = "/rotten-potatoes/controller?command=goToFilmHome";

    public AddFilmComment(UserCommentServiceImpl userCommentService) {
        this.userCommentService = userCommentService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String comment = requestContext.getRequestParameter(COMMENT_PARAMETER);
        if (comment.isEmpty()) {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_EMPTY_COMMENT);
            return CommandResult.forward(COMMENT_PAGE);
        }

        int userId = (Integer) requestContext.getSessionAttribute(USER_ID_PARAMETER);
        Film film = (Film) requestContext.getSessionAttribute(FILM);
        int filmId = film.getId();

        UserComment userComment = new UserComment(null, comment, filmId, userId);
        userCommentService.addComment(userComment);
        return CommandResult.redirect(GO_TO_FILM_HOME);
    }
}
