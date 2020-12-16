package com.epam.web.rotten.potatoes.command;

import com.epam.web.rotten.potatoes.command.impl.*;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.service.action.UserActionServiceImpl;
import com.epam.web.rotten.potatoes.service.film.FilmServiceImpl;
import com.epam.web.rotten.potatoes.service.user.UserServiceImpl;

public class CommandFactory {
    private static final String GO_TO_LOGIN = "goToLogin";
    private static final String LOGIN_PAGE = "WEB-INF/views/login.jsp";

    private static final String GO_TO_MAIN = "goToMain";
    private static final String MAIN_PAGE = "WEB-INF/views/main.jsp";

    private static final String GO_TO_FILMS = "goToFilms";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";

    private static final String GO_TO_HOME = "goToHome";
    private static final String HOME_PAGE = "WEB-INF/views/home.jsp";

    private static final String GO_TO_REVIEW = "goToReview";
    private static final String REVIEW_PAGE = "WEB-INF/views/review.jsp";

    private static final String GO_TO_COMMENT = "goToComment";
    private static final String COMMENT_PAGE = "WEB-INF/views/comment.jsp";

    private static final String GO_TO_FILM_HOME = "goToFilmHome";
    private static final String FILM_HOME_PAGE = "WEB-INF/views/film-home.jsp";

    private static final String LOGIN = "login";
    private static final String LOGOUT = "logout";
    private static final String FILMS = "films";
    private static final String USERS = "users";
    private static final String SORT_FILMS = "sort-films";
    private static final String FILM_HOME = "film-home";
    private static final String USER_EDIT = "admin-user-edit";
    private static final String ADD_REVIEW_AND_RATE = "review-rate";

    public static Command create(String command) {
        switch(command) {
            case GO_TO_HOME:
                return new GoToPage(HOME_PAGE);
            case GO_TO_LOGIN:
                return new GoToPage(LOGIN_PAGE);
            case GO_TO_MAIN:
                return new GoToPage(MAIN_PAGE);
            case GO_TO_FILMS:
                return new GoToPage(FILMS_PAGE);
            case GO_TO_REVIEW:
                return new GoToPage(REVIEW_PAGE);
            case GO_TO_COMMENT:
                return new GoToPage(COMMENT_PAGE);
            case GO_TO_FILM_HOME:
                return new GoToPage(FILM_HOME_PAGE);
            case ADD_REVIEW_AND_RATE:
                return new AddFilmRateAndReview(new UserActionServiceImpl(new DaoHelperFactory()));
            case LOGIN:
                return new Login(new UserServiceImpl(new DaoHelperFactory()));
            case LOGOUT:
                return new Logout();
            case USER_EDIT:
                return new GetUserById(new UserServiceImpl(new DaoHelperFactory()));
            case FILM_HOME:
                return new GetFilmById(new FilmServiceImpl(new DaoHelperFactory()));
            case SORT_FILMS:
                return new SortFilm(new FilmServiceImpl(new DaoHelperFactory()));
            case FILMS:
                return new FilmList(new FilmServiceImpl(new DaoHelperFactory()));
            case USERS:
                return new TopUsers(new UserServiceImpl(new DaoHelperFactory()));
            default:
                throw new IllegalArgumentException();
        }
    }
}
