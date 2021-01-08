package com.epam.web.rotten.potatoes.command.impl.user;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserComment;
import com.epam.web.rotten.potatoes.service.comment.UserCommentService;
import com.epam.web.rotten.potatoes.validator.Validator;

public class AddFilmComment implements Command {
    private final UserCommentService userCommentService;
    private final Validator<UserComment> userCommentValidator;

    private static final String COMMENT_PARAMETER = "comment";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_EMPTY_COMMENT = "errorEmptyComment";
    private static final String ERROR_LONG_COMMENT = "errorLongComment";
    private static final String COMMENT_PAGE = "WEB-INF/views/comment.jsp";
    private static final String USER_ATTRIBUTE = "user";
    private static final String FILM = "film";
    private static final String GO_TO_FILM_HOME = "/rotten-potatoes/controller?command=goToFilmHome";

    public AddFilmComment(UserCommentService userCommentService, Validator<UserComment> userCommentValidator) {
        this.userCommentService = userCommentService;
        this.userCommentValidator = userCommentValidator;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String comment = requestContext.getRequestParameter(COMMENT_PARAMETER);
        if (comment.isEmpty()) {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_EMPTY_COMMENT);
            return CommandResult.forward(COMMENT_PAGE);
        }

        Film film = (Film) requestContext.getSessionAttribute(FILM);
        int filmId = film.getId();
        User user = (User) requestContext.getSessionAttribute(USER_ATTRIBUTE);
        int userId = user.getId();
        UserComment userComment = new UserComment(null, comment, filmId, userId);
        if (userCommentValidator.isValid(userComment)) {
            userCommentService.addComment(userComment);
            return CommandResult.redirect(GO_TO_FILM_HOME);
        } else {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_LONG_COMMENT);
            return CommandResult.forward(COMMENT_PAGE);
        }
    }
}
