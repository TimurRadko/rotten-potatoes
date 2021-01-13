package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class LoginCommandTest {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final Integer USER_ID = 1;
    private static final String RIGHTS = "user";
    private static final int RATE = 50;
    private static final User USER = new User(USER_ID, LOGIN, RIGHTS, RATE, false);
    private static final User USER_BLOCKED = new User(USER_ID, LOGIN, RIGHTS, RATE,true);
    private static final String FILMS_PAGE_COMMAND = "/rotten-potatoes/controller?command=films";
    private static final String EMPTY_PARAMETER = "";
    private static final String LOGIN_PAGE = "WEB-INF/views/login.jsp";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_LOGIN = "errorLogin";
    private static final String ERROR_USER_BLOCKED = "errorBlocked";

    @Test
    public void testExecuteShouldReturnRedirectToFilmsHomePage() throws ServiceException {
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(LOGIN, EMPTY_PARAMETER);
        requestParameters.put(PASSWORD, EMPTY_PARAMETER);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        LoginCommand login = new LoginCommand(userService);
        Optional<User> optionalUser = Optional.of(USER);
        when(userService.login(anyString(), anyString())).thenReturn(optionalUser);

        CommandResult actual = login.execute(context);

        CommandResult expected = CommandResult.redirect(FILMS_PAGE_COMMAND);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecuteShouldReturnForwardToLoginPage() throws ServiceException {
        UserService userService = Mockito.mock(UserService.class);
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        LoginCommand login = new LoginCommand(userService);
        Optional<User> optionalUser = Optional.empty();
        when(userService.login(anyString(), anyString())).thenReturn(optionalUser);

        CommandResult expected = CommandResult.forward(LOGIN_PAGE);

        CommandResult actual = login.execute(context);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecuteShouldPutToContextErrorLoginMessage() throws ServiceException {
        UserService userService = Mockito.mock(UserService.class);
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        LoginCommand login = new LoginCommand(userService);
        Optional<User> optionalUser = Optional.empty();
        when(userService.login(anyString(), anyString())).thenReturn(optionalUser);

        login.execute(context);

        String actualError = (String) context.getRequestAttribute(ERROR_MESSAGE_ATTRIBUTE);
        Assert.assertEquals(ERROR_LOGIN, actualError);
    }

    @Test
    public void testExecuteShouldPutToContextErrorRightsMessage() throws ServiceException {
        UserService userService = Mockito.mock(UserService.class);
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        LoginCommand login = new LoginCommand(userService);
        Optional<User> optionalUser = Optional.of(USER_BLOCKED);
        when(userService.login(anyString(), anyString())).thenReturn(optionalUser);

        login.execute(context);

        String actualError = (String) context.getRequestAttribute(ERROR_MESSAGE_ATTRIBUTE);
        Assert.assertEquals(ERROR_USER_BLOCKED, actualError);
    }
}
