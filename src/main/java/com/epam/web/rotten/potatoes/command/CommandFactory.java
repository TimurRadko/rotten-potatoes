package com.epam.web.rotten.potatoes.command;

import com.epam.web.rotten.potatoes.command.impl.*;
import com.epam.web.rotten.potatoes.command.impl.admin.*;
import com.epam.web.rotten.potatoes.command.impl.user.AddFilmComment;
import com.epam.web.rotten.potatoes.command.impl.user.AddFilmRateAndReview;
import com.epam.web.rotten.potatoes.command.impl.user.ShowFilmComment;
import com.epam.web.rotten.potatoes.command.impl.user.ShowFilmReview;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.service.action.UserActionServiceImpl;
import com.epam.web.rotten.potatoes.service.comment.UserCommentServiceImpl;
import com.epam.web.rotten.potatoes.service.film.FilmServiceImpl;
import com.epam.web.rotten.potatoes.service.user.UserServiceImpl;
import com.epam.web.rotten.potatoes.validator.FilmValidator;
import com.epam.web.rotten.potatoes.validator.UserActionValidator;
import com.epam.web.rotten.potatoes.validator.UserCommentValidator;

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

    //Command For Unregistered Users And Others Type of Users
    private static final String LOGIN = "login";
    private static final String LOGOUT = "logout";
    private static final String GET_FILMS_LIST = "films";
    private static final String GET_USER_LIST = "users";
    private static final String GET_FILM_BY_ID = "film-home";
    private static final String GET_FILM_BY_DIRECTOR = "director";

    //For Users Only
    private static final String ADD_REVIEW_AND_RATE = "review-rate";
    private static final String ADD_COMMENT = "comment";

    //For Users and Admin
    private static final String SHOW_REVIEWS = "show-reviews";
    private static final String SHOW_COMMENTS = "show-comments";

    //For Admin Only
    private static final String ADMIN_GO_TO_ADD_FILM = "admin-goToAddFilm";
    private static final String ADMIN_FILM_ADD_PAGE = "WEB-INF/views/film-add.jsp";
    private static final String ADMIN_GO_TO_EDIT_FILM = "admin-goToEditFilm";
    private static final String ADMIN_FILM_EDIT_PAGE = "WEB-INF/views/film-edit.jsp";

    private static final String ADMIN_USER_EDIT = "admin-user-edit";
    private static final String ADMIN_BLOCK_UNBLOCK_USER = "admin-block-unblock";
    private static final String ADMIN_EDIT_USER_RATE = "admin-edit-user-rate";
    private static final String ADMIN_ADD_FILM = "admin-add-film";
    private static final String ADMIN_EDIT_FILM = "admin-edit-film";
    private static final String ADMIN_DELETE_FILM = "admin-delete-film";
    private static final String ADMIN_DELETE_COMMENT = "admin-delete-comment";
    private static final String ADMIN_DELETE_REVIEW = "admin-delete-review";

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
            case ADMIN_GO_TO_ADD_FILM:
                return new GoToPage(ADMIN_FILM_ADD_PAGE);
            case ADMIN_GO_TO_EDIT_FILM:
                return new GoToPage(ADMIN_FILM_EDIT_PAGE);

            case LOGIN:
                return new Login(new UserServiceImpl(new DaoHelperFactory()));
            case LOGOUT:
                return new Logout();
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

            case ADD_REVIEW_AND_RATE:
                return new AddFilmRateAndReview(new UserActionServiceImpl(new DaoHelperFactory()),
                        new UserActionValidator());
            case ADD_COMMENT:
                return new AddFilmComment(new UserCommentServiceImpl(new DaoHelperFactory()) ,
                        new UserCommentValidator());

            case SHOW_REVIEWS:
                return new ShowFilmReview(new UserActionServiceImpl(new DaoHelperFactory()),
                        new UserServiceImpl(new DaoHelperFactory()));
            case SHOW_COMMENTS:
                return new ShowFilmComment(new UserCommentServiceImpl(new DaoHelperFactory()),
                        new UserServiceImpl(new DaoHelperFactory()));

            case ADMIN_USER_EDIT:
                return new GetUserById(new UserServiceImpl(new DaoHelperFactory()));
            case ADMIN_BLOCK_UNBLOCK_USER:
                return new BlockUnblockUser(new UserServiceImpl(new DaoHelperFactory()));
            case ADMIN_EDIT_USER_RATE:
                return new ChangeUserRate(new UserServiceImpl(new DaoHelperFactory()));
            case ADMIN_ADD_FILM:
                return new AddFilm(new FilmServiceImpl(new DaoHelperFactory()), new FilmValidator());
            case ADMIN_DELETE_FILM:
                return new DeleteFilm(new FilmServiceImpl(new DaoHelperFactory()));
            case ADMIN_DELETE_COMMENT:
                return new DeleteComment(new UserCommentServiceImpl(new DaoHelperFactory()));
            case ADMIN_EDIT_FILM:
                return new EditFilm(new FilmServiceImpl(new DaoHelperFactory()), new FilmValidator());
            case ADMIN_DELETE_REVIEW:
                return new DeleteReview(new UserActionServiceImpl(new DaoHelperFactory()));
            default:
                throw new IllegalArgumentException();
        }
    }
}