package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionServiceImpl;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

public class AddFilmRateAndReview implements Command {
    private final UserActionServiceImpl userActionService;
    private static final String FILM_RATE_PARAMETER = "film_rate";
    private static final String REVIEW_PARAMETER = "review";
    private static final String USER_ID_PARAMETER = "user_id";
    private static final String FILM_ID_PARAMETER = "film_id";
    private static final String MAIN_PAGE = "WEB-INF/views/main.jsp";
    private static final String GO_TO_FILM_HOME = "/rotten-potatoes/controller?command=goToFilmHome";


    public AddFilmRateAndReview(UserActionServiceImpl userActionService) {
        this.userActionService = userActionService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int userId = (Integer) requestContext.getSessionAttribute(USER_ID_PARAMETER);
        int filmId = (Integer) requestContext.getSessionAttribute(FILM_ID_PARAMETER);

//        String filmRateString = requestContext.getRequestParameter(FILM_RATE_PARAMETER);
//        int filmRate = Integer.parseInt(filmRateString);
        int rate = 10;
        String review = "Hello";
//        String review = requestContext.getRequestParameter(REVIEW_PARAMETER);

        UserAction userAction = new UserAction(null, rate, review,  userId, filmId);
        userActionService.addReviewAndRate(userAction);
        return CommandResult.redirect(GO_TO_FILM_HOME);
    }
}
