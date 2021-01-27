package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class DeleteReviewCommandTest {
    private static final String ID_PARAMETER = "id";
    private static final String ID_VALUE = "1";

    private static final int USER_ACTION_ID_VALUE = 1;
    private static final int FILM_RATE_VALUE = 10;
    private static final String REVIEW_VALUE = "Hello";
    private static final int USER_ID_VALUE = 1;
    private static final int FILM_ID_VALUE = 1;
    private static final UserAction USER_ACTION =
            new UserAction(USER_ACTION_ID_VALUE, FILM_RATE_VALUE, REVIEW_VALUE, USER_ID_VALUE, FILM_ID_VALUE);

    private static final String INDEX_PAGE = "index.jsp";

    @Test
    public void testExecute() throws ServiceException {
        //given
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, ID_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        DeleteReviewCommand deleteReview = new DeleteReviewCommand(userActionService);
        //when
        when(userActionService.getReviewById(anyInt())).thenReturn(Optional.of(USER_ACTION));
        CommandResult actual = deleteReview.execute(context);
        //then
        CommandResult expected = CommandResult.redirect(INDEX_PAGE);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ServiceException.class)//then
    public void testExecuteShouldThrowServiceExceptionWhenIdParameterEqualsNull() throws ServiceException {
        //given
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, null);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        DeleteReviewCommand deleteReview = new DeleteReviewCommand(userActionService);
        //when
        deleteReview.execute(context);
    }
}