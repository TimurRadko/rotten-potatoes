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
    private static final User UNBLOCKED_USER = new User(1,"A", "user", 10, false);
    private static final String ID_PARAMETER = "id";
    private static final String ID_VALUE = "1";
    private static final String USERS_PAGE_COMMAND = "/rotten-potatoes/controller?command=users";

    @Test
    public void testExecuteShouldChangeUserBlockedParameter() throws ServiceException {
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, ID_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        BlockUnblockUserCommand blockUnblockUser = new BlockUnblockUserCommand(userService);
        when(userService.getUserById(anyInt())).thenReturn(Optional.of(UNBLOCKED_USER));
        CommandResult actual = blockUnblockUser.execute(context);

        CommandResult expected = CommandResult.redirect(USERS_PAGE_COMMAND);
        Assert.assertEquals(expected, actual);
    }
}
