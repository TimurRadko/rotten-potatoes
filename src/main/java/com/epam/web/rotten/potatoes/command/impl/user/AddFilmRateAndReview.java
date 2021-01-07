package com.epam.web.rotten.potatoes.command.impl.user;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.ArrayList;
import java.util.List;

public class AddFilmRateAndReview implements Command {
    private final UserActionService userActionService;
    private static final String FILM = "film";
    private static final String FILM_RATE_PARAMETER = "film_rate";
    private static final String REVIEW_PARAMETER = "review";
    private static final String USER_ATTRIBUTE = "user";
    private static final String GO_TO_FILM_HOME = "/rotten-potatoes/controller?command=goToFilmHome";
    private static final String REVIEW_PAGE = "WEB-INF/views/review.jsp";

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_EMPTY_REVIEW = "errorEmptyReview";
    private static final String ERROR_REPEATED_REVIEW = "errorRepeatedReview";

    public AddFilmRateAndReview(UserActionService userActionService) {
        this.userActionService = userActionService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String review = requestContext.getRequestParameter(REVIEW_PARAMETER);
        if (review.isEmpty()) {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_EMPTY_REVIEW);
            return CommandResult.forward(REVIEW_PAGE);
        }

        User user = (User) requestContext.getSessionAttribute(USER_ATTRIBUTE);
        int userId = user.getId();
        Film film = (Film) requestContext.getSessionAttribute(FILM);
        int filmId = film.getId();

        String rateString = requestContext.getRequestParameter(FILM_RATE_PARAMETER);
        int rate = Integer.parseInt(rateString);

        if (isUserWroteReview(userId, filmId)) {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_REPEATED_REVIEW);
            return CommandResult.forward(REVIEW_PAGE);
        }

        UserAction userAction = new UserAction(null, rate, review, userId, filmId);
        userActionService.addReviewAndRate(userAction);
        return CommandResult.redirect(GO_TO_FILM_HOME);
    }

    private boolean isUserWroteReview(Integer userId, Integer filmId) throws ServiceException {
        List<UserAction> userActions = userActionService.findReviewsByUserId(userId);
        List<Integer> findingFilmIds = new ArrayList<>();
        for (UserAction userAction : userActions) {
            Integer findingFilmId = userAction.getFilmId();
            findingFilmIds.add(findingFilmId);
        }
        return findingFilmIds.contains(filmId);
    }
}