package com.epam.web.rotten.potatoes.command.impl.user;

import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserComment;
import com.epam.web.rotten.potatoes.service.comment.UserCommentService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class AddFilmCommentCommandTest {
    private static final String COMMENT_PARAMETER = "comment";
    private static final String EMPTY_COMMENT_VALUE = "";
    private static final String USER_PARAMETER = "user";
    private static final String FILM_PARAMETER = "film";

    private static final int FILM_ID_VALUE = 1;
    private static final String TITLE_VALUE = "Valid Title";
    private static final String DIRECTOR_VALUE = "Valid Director";
    private static final String POSTER_VALUE = "Valid Poster";
    private static final int DEFAULT_RATE_VALUE = 10;
    private static final Film FILM = new Film(FILM_ID_VALUE, TITLE_VALUE, DIRECTOR_VALUE, POSTER_VALUE, DEFAULT_RATE_VALUE);

    private static final int USER_ID_VALUE = 1;
    private static final String LOGIN_VALUE = "Valid Login";
    private static final String RIGHTS_VALUE = "user";
    private static final int RATE_VALUE = 10;
    private static final User USER = new User(USER_ID_VALUE, LOGIN_VALUE, RIGHTS_VALUE, RATE_VALUE, false);

    private static final int USER__COMMENT_ID_VALUE = 1;
    private static final String COMMENT_VALUE = "Hello";
    private static final UserComment USER_COMMENT =
            new UserComment(USER__COMMENT_ID_VALUE, COMMENT_VALUE, FILM_ID_VALUE, USER_ID_VALUE);

    private static final String FILM_HOME_PAGE_COMMAND = "/rotten-potatoes/controller?command=film-home&id=1";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_EMPTY_COMMENT = "errorEmptyComment";
    private static final String ERROR_LONG_COMMENT = "errorLongComment";
    private static final String LONG_COMMENT = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
            "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis " +
            "parturient montes, nascetur ridiculus mus. Donec quo";

    @Test
    public void testExecuteShouldReturnRedirectWhenUserCommentIsValid() throws ServiceException {
        UserCommentService userCommentService = Mockito.mock(UserCommentService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(COMMENT_PARAMETER, COMMENT_VALUE);
        Map<String, Object> sessionAttribute = putUserAttributeToSession();
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, sessionAttribute);
        AddFilmCommentCommand addFilmComment = new AddFilmCommentCommand(userCommentService);
        Optional<UserComment> optionalUserComment = Optional.of(USER_COMMENT);
        when(userCommentService.findCommentById(anyInt())).thenReturn(optionalUserComment);

        CommandResult actual = addFilmComment.execute(context);

        CommandResult expected = CommandResult.redirect(FILM_HOME_PAGE_COMMAND);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecuteShouldPutToContextErrorMessageWhenCommentEmpty() throws ServiceException {
        UserCommentService userCommentService = Mockito.mock(UserCommentService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(COMMENT_PARAMETER, EMPTY_COMMENT_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        AddFilmCommentCommand addFilmComment = new AddFilmCommentCommand(userCommentService);

        addFilmComment.execute(context);

        String actualError = (String) context.getRequestAttribute(ERROR_MESSAGE_ATTRIBUTE);
        Assert.assertEquals(ERROR_EMPTY_COMMENT, actualError);
    }

    @Test
    public void testExecuteShouldPutToContextErrorMessageWhenUserWroteLongComment() throws ServiceException {
        UserCommentService userCommentService = Mockito.mock(UserCommentService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(COMMENT_PARAMETER, LONG_COMMENT);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        AddFilmCommentCommand addFilmComment = new AddFilmCommentCommand(userCommentService);

        addFilmComment.execute(context);

        String actualError = (String) context.getRequestAttribute(ERROR_MESSAGE_ATTRIBUTE);
        Assert.assertEquals(ERROR_LONG_COMMENT, actualError);
    }

    private Map<String, Object> putUserAttributeToSession() {
        Map<String, Object> sessionAttribute = new HashMap<>();
        sessionAttribute.put(USER_PARAMETER, USER);
        sessionAttribute.put(FILM_PARAMETER, FILM);
        return sessionAttribute;
    }
}