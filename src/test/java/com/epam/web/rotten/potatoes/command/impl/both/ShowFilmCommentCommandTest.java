package com.epam.web.rotten.potatoes.command.impl.both;

import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserComment;
import com.epam.web.rotten.potatoes.service.comment.UserCommentService;
import com.epam.web.rotten.potatoes.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class ShowFilmCommentCommandTest {
    private static final String FILM_PARAMETER = "film";

    private static final int FILM_ID_VALUE = 1;
    private static final String TITLE_VALUE = "Valid Title";
    private static final String DIRECTOR_VALUE = "Valid Director";
    private static final String POSTER_VALUE = "Valid Poster";
    private static final int DEFAULT_RATE_VALUE = 10;
    private static final Film FILM = new Film(FILM_ID_VALUE, TITLE_VALUE, DIRECTOR_VALUE,
            POSTER_VALUE, DEFAULT_RATE_VALUE);

    private static final int USER_COMMENT_ID_VALUE = 1;
    private static final String USER_COMMENT_VALUE = "Valid Comment";
    private static final int USER_ID_VALUE = 1;
    private static final UserComment USER_COMMENT = new UserComment(USER_COMMENT_ID_VALUE, USER_COMMENT_VALUE,
            FILM_ID_VALUE, USER_ID_VALUE);

    private static final String VALID_LOGIN = "Valid Login";
    private static final String VALID_RIGHTS = "user";
    private static final int RATE_VALUE = 10;
    private static final User USER = new User(USER_ID_VALUE, VALID_LOGIN, VALID_RIGHTS,
            RATE_VALUE, false);

    private static final List<UserComment> USER_COMMENTS = Collections.singletonList(USER_COMMENT);
    private static final String FILM_HOME_PAGE = "WEB-INF/views/film-home.jsp";

    @Test
    public void testExecuteShouldReturnForwardFilmHomePage() throws ServiceException {
        UserCommentService userCommentService = Mockito.mock(UserCommentService.class);
        UserService userService = Mockito.mock(UserService.class);
        Map<String, Object> sessionParameters = new HashMap<>();
        sessionParameters.put(FILM_PARAMETER, FILM);
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), sessionParameters);
        ShowFilmCommentCommand showFilmComment = new ShowFilmCommentCommand(userCommentService, userService);
        when(userCommentService.findCommentsByFilmId(anyInt())).thenReturn(USER_COMMENTS);
        when(userService.getUserById(USER_ID_VALUE)).thenReturn(Optional.of(USER));
        CommandResult actual = showFilmComment.execute(context);

        CommandResult expected = CommandResult.forward(FILM_HOME_PAGE);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ServiceException.class)
    public void testExecuteShouldThrowServiceExceptionWhenIdParameterEqualsNull() throws ServiceException {
        UserCommentService userCommentService = Mockito.mock(UserCommentService.class);
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(FILM_PARAMETER, null);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        ShowFilmCommentCommand showFilmCommentCommand = new ShowFilmCommentCommand(userCommentService, userService);
        showFilmCommentCommand.execute(context);
    }
}
