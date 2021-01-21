package com.epam.web.rotten.potatoes.command.impl.both;

import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import com.epam.web.rotten.potatoes.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class ShowFilmReviewCommandTest {
    private static final String FILM_PARAMETER = "film";

    private static final int FILM_ID_VALUE = 1;
    private static final String TITLE_VALUE = "Valid Title";
    private static final String DIRECTOR_VALUE = "Valid Director";
    private static final String POSTER_VALUE = "Valid Poster";
    private static final int DEFAULT_RATE_VALUE = 10;
    private static final Film FILM = new Film(FILM_ID_VALUE, TITLE_VALUE, DIRECTOR_VALUE,
            POSTER_VALUE, DEFAULT_RATE_VALUE);

    private static final int USER_ID_VALUE = 1;
    private static final int USER_ACTION_ID_VALUE = 1;
    private static final int FILM_RATE_VALUE = 10;
    private static final String REVIEW_VALUE = "Hello";
    private static final UserAction USER_ACTION =
            new UserAction(USER_ACTION_ID_VALUE, FILM_RATE_VALUE, REVIEW_VALUE, USER_ID_VALUE, FILM_ID_VALUE);

    private static final String VALID_LOGIN = "Valid Login";
    private static final String VALID_RIGHTS = "user";
    private static final int RATE_VALUE = 10;
    private static final User USER = new User(USER_ID_VALUE, VALID_LOGIN, VALID_RIGHTS,
            RATE_VALUE, false);

    private static final List<UserAction> USER_ACTIONS = Collections.singletonList(USER_ACTION);
    private static final String FILM_HOME_PAGE = "WEB-INF/views/film-home.jsp";

    @Test
    public void testExecuteShouldReturnForwardFilmHomePage() throws ServiceException {
        //given
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        UserService userService = Mockito.mock(UserService.class);
        Map<String, Object> sessionParameters = new HashMap<>();
        sessionParameters.put(FILM_PARAMETER, FILM);
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), sessionParameters);
        ShowFilmReviewCommand showFilmReview = new ShowFilmReviewCommand(userActionService, userService);
        //when
        when(userActionService.getReviewsByFilmId(anyInt())).thenReturn(USER_ACTIONS);
        when(userService.getUserById(USER_ID_VALUE)).thenReturn(Optional.of(USER));
        CommandResult actual = showFilmReview.execute(context);
        //then
        CommandResult expected = CommandResult.forward(FILM_HOME_PAGE);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ServiceException.class)//then
    public void testExecuteShouldThrowServiceExceptionWhenIdParameterEqualsNull() throws ServiceException {
        //given
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(FILM_PARAMETER, null);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        ShowFilmReviewCommand showFilmReview = new ShowFilmReviewCommand(userActionService, userService);
        //when
        showFilmReview.execute(context);
    }
}
