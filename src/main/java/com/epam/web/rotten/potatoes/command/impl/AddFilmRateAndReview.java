package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.service.action.UserActionServiceImpl;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.Optional;

public class AddFilmRateAndReview implements Command {
    private final UserActionServiceImpl userActionService;
    private static final String USER_ID_PARAMETER = "user_id";
    private static final String REVIEW_PARAMETER = "review";
    private static final String MAIN_PAGE = "WEB-INF/views/main.jsp";
    private static final String GO_TO_FILM_HOME = "/rotten-potatoes/controller?command=goToFilmHome";

    public AddFilmRateAndReview(UserActionServiceImpl userActionService) {
        this.userActionService = userActionService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int userId = Integer.parseInt(requestContext.getRequestParameter(USER_ID_PARAMETER));

//        Optional<UserAction> userAction = userActionService.addReview();

//        if (review == null) {
//            return CommandResult.forward(MAIN_PAGE);
//        } else {
//            return CommandResult.redirect(GO_TO_FILM_HOME);
//        }
        return null;
    }
}
