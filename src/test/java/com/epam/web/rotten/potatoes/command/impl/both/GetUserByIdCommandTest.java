package com.epam.web.rotten.potatoes.command.impl.both;

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

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class GetUserByIdCommandTest {
    private static final String ID_PARAMETER = "id";
    private static final String ID_VALUE = "1";
    private static final String USER_EDIT_PAGE = "WEB-INF/views/user-edit.jsp";

    private static final int USER_ID_VALUE = 1;
    private static final String VALID_LOGIN = "Valid Login";
    private static final String VALID_RIGHTS = "user";
    private static final int RATE_VALUE = 10;
    private static final User USER = new User(USER_ID_VALUE, VALID_LOGIN, VALID_RIGHTS,
            RATE_VALUE, false);

    @Test
    public void testExecuteShouldReturnForwardWhenUserIsExists() throws ServiceException {
        //given
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, ID_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        GetUserByIdCommand getUserById = new GetUserByIdCommand(userService);
        //when
        when(userService.getUserById(anyInt())).thenReturn(Optional.of(USER));
        CommandResult actual = getUserById.execute(context);
        //then
        CommandResult expected = CommandResult.forward(USER_EDIT_PAGE);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ServiceException.class)//then
    public void testExecuteShouldThrowServiceExceptionWhenIdParameterEqualsNull() throws ServiceException {
        //given
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, null);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        GetUserByIdCommand getUserById = new GetUserByIdCommand(userService);
        //when
        getUserById.execute(context);
    }
}