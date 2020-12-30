package com.epam.web.rotten.potatoes.command;

import com.epam.web.rotten.potatoes.command.impl.*;
import com.epam.web.rotten.potatoes.command.impl.admin.AddFilm;
import com.epam.web.rotten.potatoes.command.impl.admin.AddUserRate;
import com.epam.web.rotten.potatoes.command.impl.admin.BlockUnblockUser;
import com.epam.web.rotten.potatoes.command.impl.admin.ReduceUserRate;
import com.epam.web.rotten.potatoes.command.impl.user.AddFilmComment;
import com.epam.web.rotten.potatoes.command.impl.user.AddFilmRateAndReview;
import com.epam.web.rotten.potatoes.command.impl.user.ShowFilmComment;
import com.epam.web.rotten.potatoes.command.impl.user.ShowFilmReview;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.service.action.UserActionServiceImpl;
import com.epam.web.rotten.potatoes.service.comment.UserCommentServiceImpl;
import com.epam.web.rotten.potatoes.service.film.FilmServiceImpl;
import com.epam.web.rotten.potatoes.service.user.UserServiceImpl;

public class CommandFactory {
    private static final String GO_TO_LOGIN = "goToLogin";
    private static final String LOGIN_PAGE = "WEB-INF/views/login.jsp";

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
    private static final String GET_FILMS_LIST = "films";
    private static final String GET_USER_LIST = "users";
    private static final String GET_FILM_BY_ID = "film-home";
    private static final String ADD_REVIEW_AND_RATE = "review-rate";
    private static final String SHOW_REVIEWS = "show-reviews";
    private static final String SHOW_COMMENTS = "show-comments";
    private static final String GET_FILM_BY_DIRECTOR = "director";
    private static final String ADD_COMMENT = "comment";

    private static final String ADMIN_GO_TO_SAVE_FILM = "admin-goToEditFilm";
    private static final String FILM_SAVE_PAGE = "WEB-INF/views/film-save.jsp";

    private static final String ADMIN_USER_EDIT = "admin-user-edit";
    private static final String ADMIN_BLOCK_UNBLOCK_USER = "admin-block-unblock";
    private static final String ADMIN_ADD_USER_RATE = "admin-add-user-rate";
    private static final String ADMIN_REDUCE_USER_RATE = "admin-reduce-user-rate";
    private static final String ADMIN_ADD_FILM = "admin-add-film";

    public static Command create(String command) {
        switch (command) {
            case GO_TO_HOME:
                return new GoToPage(HOME_PAGE);
            case GO_TO_LOGIN:
                return new GoToPage(LOGIN_PAGE);
            case GO_TO_FILMS:
                return new GoToPage(FILMS_PAGE);
            case GO_TO_REVIEW:
                return new GoToPage(REVIEW_PAGE);
            case GO_TO_COMMENT:
                return new GoToPage(COMMENT_PAGE);
            case GO_TO_FILM_HOME:
                return new GoToPage(FILM_HOME_PAGE);
            case ADMIN_GO_TO_SAVE_FILM:
                return new GoToPage(FILM_SAVE_PAGE);

            case LOGIN:
                return new Login(new UserServiceImpl(new DaoHelperFactory()));
            case LOGOUT:
                return new Logout();

            case ADD_REVIEW_AND_RATE:
                return new AddFilmRateAndReview(new UserActionServiceImpl(new DaoHelperFactory()));
            case SHOW_REVIEWS:
                return new ShowFilmReview(new UserActionServiceImpl(new DaoHelperFactory()),
                        new UserServiceImpl(new DaoHelperFactory()));
            case GET_FILM_BY_ID:
                return new GetFilmById(new FilmServiceImpl(new DaoHelperFactory()),
                        new UserActionServiceImpl(new DaoHelperFactory()));
            case GET_FILM_BY_DIRECTOR:
                return new GetFilmsByDirector(new FilmServiceImpl(new DaoHelperFactory()));
            case GET_FILMS_LIST:
                return new GetFilmList(new FilmServiceImpl(new DaoHelperFactory()),
                        new UserActionServiceImpl(new DaoHelperFactory()));
            case GET_USER_LIST:
                return new GetUserList(new UserServiceImpl(new DaoHelperFactory()));
            case ADD_COMMENT:
                return new AddFilmComment(new UserCommentServiceImpl(new DaoHelperFactory()));
            case SHOW_COMMENTS:
                return new ShowFilmComment(new UserCommentServiceImpl(new DaoHelperFactory()),
                        new UserServiceImpl(new DaoHelperFactory()));

            case ADMIN_USER_EDIT:
                return new GetUserById(new UserServiceImpl(new DaoHelperFactory()));
            case ADMIN_BLOCK_UNBLOCK_USER:
                return new BlockUnblockUser(new UserServiceImpl(new DaoHelperFactory()));
            case ADMIN_ADD_USER_RATE:
                return new AddUserRate(new UserServiceImpl(new DaoHelperFactory()));
            case ADMIN_REDUCE_USER_RATE:
                return new ReduceUserRate(new UserServiceImpl(new DaoHelperFactory()));
            case ADMIN_ADD_FILM:
                return new AddFilm(new FilmServiceImpl(new DaoHelperFactory()));
            default:
                throw new IllegalArgumentException();
        }
    }
}
