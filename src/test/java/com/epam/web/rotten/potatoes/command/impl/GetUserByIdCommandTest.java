package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.command.impl.both.GetUserByIdCommand;
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

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class GetUserByIdCommandTest {
    private static final int USER_ID_VALUE = 1;
    private static final String LOGIN_VALUE = "Valid Login";
    private static final String RIGHTS_VALUE = "user";
    private static final int RATE_VALUE = 50;
    private static final User USER = new User(USER_ID_VALUE, LOGIN_VALUE, RIGHTS_VALUE,
            RATE_VALUE, false);

    private static final String ID_PARAMETER = "id";
    private static final String ID_VALUE = "1";
    private static final String INVALID_ID_VALUE = "2";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";
    private static final String USER_EDIT_PAGE = "WEB-INF/views/user-edit.jsp";

    @Test
    public void testExecuteShouldReturnForwardUserEditPageWhenUserFound() throws ServiceException {
        //given
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, ID_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        GetUserByIdCommand getUserById = new GetUserByIdCommand(userService);
        Optional<User> optionalUser = Optional.of(USER);
        //when
        when(userService.getUserById(anyInt())).thenReturn(optionalUser);
        CommandResult actual = getUserById.execute(context);
        //then
        CommandResult expected = CommandResult.forward(USER_EDIT_PAGE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecuteShouldReturnForwardFilmsPageWhenUserNotFound() throws ServiceException {
        //given
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, INVALID_ID_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        GetUserByIdCommand getUserById = new GetUserByIdCommand(userService);
        Optional<User> optionalUser = Optional.empty();
        //when
        when(userService.getUserById(anyInt())).thenReturn(optionalUser);
        CommandResult actual = getUserById.execute(context);
        //then
        CommandResult expected = CommandResult.forward(FILMS_PAGE);
        Assert.assertEquals(expected, actual);
    }
}