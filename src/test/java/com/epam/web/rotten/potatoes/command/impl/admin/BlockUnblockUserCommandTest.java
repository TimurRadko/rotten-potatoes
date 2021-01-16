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

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class BlockUnblockUserCommandTest {
    private static final int USER_ID_VALUE = 1;
    private static final String VALID_LOGIN = "Valid Login";
    private static final String VALID_RIGHTS = "user";
    private static final int RATE_VALUE = 10;
    private static final User UNBLOCKED_USER = new User(USER_ID_VALUE, VALID_LOGIN, VALID_RIGHTS,
            RATE_VALUE, false);

    private static final String ID_PARAMETER = "id";
    private static final String ID_VALUE = "1";
    private static final String USERS_PAGE_COMMAND = "/rotten-potatoes/controller?command=users";

    @Test
    public void testExecuteShouldReturnRedirectWhenBlockUnblockUser() throws ServiceException {
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, ID_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        BlockUnblockUserCommand blockUnblockUser = new BlockUnblockUserCommand(userService);
        when(userService.getUserById(anyInt())).thenReturn(Optional.of(UNBLOCKED_USER));
        when(userService.save(UNBLOCKED_USER)).thenReturn(Optional.of(USER_ID_VALUE));
        CommandResult actual = blockUnblockUser.execute(context);

        CommandResult expected = CommandResult.redirect(USERS_PAGE_COMMAND);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ServiceException.class)
    public void testExecuteShouldThrowServiceExceptionWhenIdParameterEqualsNull() throws ServiceException {
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, null);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        BlockUnblockUserCommand blockUnblockUser = new BlockUnblockUserCommand(userService);
        blockUnblockUser.execute(context);
    }
}
