package com.epam.web.rotten.potatoes.command.impl.user;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.model.dto.UserReviewDto;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import com.epam.web.rotten.potatoes.service.user.UserService;

import java.util.*;

public class ShowFilmReview implements Command {
    private final UserActionService userActionService;
    private final UserService userService;

    private static final String USER_REVIEWS = "user_reviews";
    private static final String FILM = "film";
    private static final String FILM_HOME_PAGE = "WEB-INF/views/film-home.jsp";

    public ShowFilmReview(UserActionService userActionService, UserService userService) {
        this.userActionService = userActionService;
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        Film film = (Film) requestContext.getSessionAttribute(FILM);
        int filmId = film.getId();
        List<UserAction> actions = userActionService.findReviewsByFilmId(filmId);
        List<UserReviewDto> usersAndReviews = getAllReviewsAndLogin(actions);
        requestContext.setRequestAttribute(USER_REVIEWS, usersAndReviews);
        return CommandResult.forward(FILM_HOME_PAGE);
    }

    private List<UserReviewDto> getAllReviewsAndLogin(List<UserAction> actions) throws ServiceException {
        List<UserReviewDto> userReviewDtos = new ArrayList<>();

        for (UserAction action : actions) {
            int userId = action.getUserId();
            Integer actionId = action.getId();
            String review = action.getReview();
            double filmRate = action.getFilmRate();
            Optional<User> optionalUser = userService.getUserById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String login = user.getLogin();
                UserReviewDto userReviewDto = new UserReviewDto(actionId, login, review, filmRate);
                userReviewDtos.add(userReviewDto);
            }
        }
        return userReviewDtos;
    }
}