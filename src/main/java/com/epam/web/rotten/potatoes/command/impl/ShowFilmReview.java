package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionService;

import java.util.List;

public class ShowFilmReview implements Command {
    private final UserActionService userActionService;
    private static final String FILM_ID_PARAMETER = "film_id";
    private static final String USER_ACTIONS = "user_actions";
    private static final String FILM_HOME = "WEB-INF/views/film-home.jsp";

    public ShowFilmReview(UserActionService userActionService) {
        this.userActionService = userActionService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int filmId = (Integer) requestContext.getSessionAttribute(FILM_ID_PARAMETER);
        List<UserAction> actions = userActionService.findAllReviewByFilmId(filmId);
        requestContext.setRequestAttribute(USER_ACTIONS, actions);
        return CommandResult.forward(FILM_HOME);
    }
}
