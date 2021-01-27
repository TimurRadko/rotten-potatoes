package com.epam.web.rotten.potatoes.command.impl.admin;

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

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

public class ChangeUserRateCommandTest {
    private static final int USER_ID_VALUE = 1;
    private static final String VALID_LOGIN = "Valid Login";
    private static final String VALID_RIGHTS = "user";
    private static final int RATE_VALUE = 10;
    private static final User USER = new User(USER_ID_VALUE, VALID_LOGIN, VALID_RIGHTS,
            RATE_VALUE, false);

    private static final String ID_PARAMETER = "id";
    private static final String ID_VALUE = "1";
    private static final String RATE_PARAMETER = "rate";
    private static final String NOT_NUMBER_RATE_PARAMETER = "Invalid";
    private static final String NEGATIVE_RATE_PARAMETER = "-5";
    private static final String USERS_PAGE_COMMAND = "/rotten-potatoes/controller?command=users";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_ENTER_RATE = "errorRate";
    private static final String ERROR_NEGATIVE_RATE = "negativeRate";

    @Test
    public void testExecuteShouldReturnRedirectWhenUserRateWillChange() throws ServiceException {
        //given
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, ID_VALUE);
        requestParameters.put(RATE_PARAMETER, String.valueOf(RATE_VALUE));
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        ChangeUserRateCommand changeUserRate = new ChangeUserRateCommand(userService);
        //when
        when(userService.getUserById(anyObject())).thenReturn(Optional.of(USER));
        when(userService.save(USER)).thenReturn(Optional.of(USER_ID_VALUE));
        CommandResult actual = changeUserRate.execute(context);
        //then
        CommandResult expected = CommandResult.redirect(USERS_PAGE_COMMAND);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecuteShouldPutToContextErrorMessageWhenRateIsNotNumber() throws ServiceException {
        //given
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, ID_VALUE);
        requestParameters.put(RATE_PARAMETER, NOT_NUMBER_RATE_PARAMETER);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        //when
        when(userService.getUserById(anyObject())).thenReturn(Optional.of(USER));
        ChangeUserRateCommand changeUserRate = new ChangeUserRateCommand(userService);
        changeUserRate.execute(context);
        //then
        String actualError = (String) context.getRequestAttribute(ERROR_MESSAGE_ATTRIBUTE);
        Assert.assertEquals(ERROR_ENTER_RATE, actualError);
    }

    @Test
    public void testExecuteShouldPutToContextErrorMessageWhenRateIsNegative() throws ServiceException {
        //given
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, ID_VALUE);
        requestParameters.put(RATE_PARAMETER, NEGATIVE_RATE_PARAMETER);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        //when
        when(userService.getUserById(anyObject())).thenReturn(Optional.of(USER));
        ChangeUserRateCommand changeUserRate = new ChangeUserRateCommand(userService);
        changeUserRate.execute(context);
        //then
        String actualError = (String) context.getRequestAttribute(ERROR_MESSAGE_ATTRIBUTE);
        Assert.assertEquals(ERROR_NEGATIVE_RATE, actualError);
    }

    @Test(expected = ServiceException.class)//then
    public void testExecuteShouldThrowServiceExceptionWhenIdParameterEqualsNull() throws ServiceException {
        //given
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, null);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        ChangeUserRateCommand changeUserRate = new ChangeUserRateCommand(userService);
        //when
        changeUserRate.execute(context);
    }
}