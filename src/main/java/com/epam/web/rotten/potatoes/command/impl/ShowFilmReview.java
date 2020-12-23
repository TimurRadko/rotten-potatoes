package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import com.epam.web.rotten.potatoes.service.user.UserService;

import java.util.*;

public class ShowFilmReview implements Command {
    private final UserActionService userActionService;
    private final UserService userService;

    private static final String FILM_ID_PARAMETER = "film_id";
    private static final String USER_REVIEW = "user_review";
    private static final String FILM_HOME = "WEB-INF/views/film-home.jsp";

    public ShowFilmReview(UserActionService userActionService, UserService userService) {
        this.userActionService = userActionService;
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int filmId = (Integer) requestContext.getSessionAttribute(FILM_ID_PARAMETER);
        List<UserAction> actions = userActionService.findAllReviewsByFilmId(filmId);
        Map<String, UserAction> usersAndReviews = getUserReviewMap(actions);
        requestContext.setRequestAttribute(USER_REVIEW, usersAndReviews);
        return CommandResult.forward(FILM_HOME);
    }

    private Map<String, UserAction> getUserReviewMap(List<UserAction> actions) throws ServiceException {
        Map<String, UserAction> usersAndReviews = new HashMap<>();
        for (UserAction action : actions) {
            int userId = action.getUserId();
            Optional<User> user = userService.getUserById(userId);
            if (user.isPresent()) {
                User userSession = user.get();
                String login = userSession.getLogin();
                usersAndReviews.put(login, action);
            }
        }
        return usersAndReviews;
    }
}
