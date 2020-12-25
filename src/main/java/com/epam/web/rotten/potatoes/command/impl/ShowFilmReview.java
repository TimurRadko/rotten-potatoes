package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.model.dto.UserReview;
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
        List<UserAction> actions = userActionService.findReviewsByFilmId(filmId);
        List<UserReview> usersAndReviews = getAllReviewsAndLogin(actions);
        requestContext.setRequestAttribute(USER_REVIEW, usersAndReviews);

        return CommandResult.forward(FILM_HOME);
    }

    private List<UserReview> getAllReviewsAndLogin(List<UserAction> actions) throws ServiceException {
        List<UserReview> userReviews = new ArrayList<>();

        for (UserAction action : actions) {
            int userId = action.getUserId();
            Integer actionId = action.getId();
            String review = action.getReview();
            double filmRate = action.getFilmRate();
            Optional<User> user = userService.getUserById(userId);
            if (user.isPresent()) {
                User userSession = user.get();
                String userSessionLogin = userSession.getLogin();
                UserReview userReview = new UserReview(actionId, userSessionLogin, review, filmRate);
                userReviews.add(userReview);
            }
        }
        return userReviews;
    }
}