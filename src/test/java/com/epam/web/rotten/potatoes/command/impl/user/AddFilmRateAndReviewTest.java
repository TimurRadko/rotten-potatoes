package com.epam.web.rotten.potatoes.command.impl.user;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
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

public class AddFilmRateAndReviewTest {
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_EMPTY_REVIEW = "errorEmptyReview";
    private static final String ERROR_REPEATED_REVIEW = "errorRepeatedReview";
    private static final UserAction USER_ACTION =
            new UserAction(1, 5, "Hello", 1, 1);
    private static final Film FILM = new Film(1, "A", "A", "A", 5);
    private static final String REVIEW_PARAMETER = "review";
    private static final String USER_ID_PARAMETER = "user_id";
    private static final String FILM_PARAMETER = "film";
    private static final String EMPTY_REVIEW_VALUE = "";
    private static final String REVIEW_VALUE = "Hello";
    private static final String FILM_RATE_PARAMETER = "film_rate";
    private static final String FILM_RATE = "5";

    @Test
    public void testExecuteShouldPutToContextErrorMessageWhenReviewEmpty() throws ServiceException {
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(REVIEW_PARAMETER, EMPTY_REVIEW_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        when(userActionService.findReviewById(anyInt())).thenReturn(Optional.empty());
        AddFilmRateAndReview addFilmRateAndReview = new AddFilmRateAndReview(userActionService);

        addFilmRateAndReview.execute(context);

        String actualError = (String) context.getRequestAttribute(ERROR_MESSAGE_ATTRIBUTE);
        Assert.assertEquals(actualError, ERROR_EMPTY_REVIEW);
    }

//    @Test
//    public void testExecuteShouldPutToContextErrorMessageWhenUserWroteReview() throws ServiceException {
//        UserActionService userActionService = Mockito.mock(UserActionService.class);
//        Map<String, String> requestParameters = new HashMap<>();
//        requestParameters.put(REVIEW_PARAMETER, REVIEW_VALUE);
//        requestParameters.put(FILM_RATE_PARAMETER, FILM_RATE);
//        Map<String, Object> sessionAttribute = putUserAttributeToSession();
//        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, sessionAttribute);
//        Optional<UserAction> userAction = Optional.of(USER_ACTION);
//        when(userActionService.findReviewById(anyInt())).thenReturn(userAction);
//        AddFilmRateAndReview addFilmRateAndReview = new AddFilmRateAndReview(userActionService);
//
//        addFilmRateAndReview.execute(context);
//
//        String actualError = (String) context.getRequestAttribute(ERROR_MESSAGE_ATTRIBUTE);
//        Assert.assertEquals(actualError, ERROR_REPEATED_REVIEW);
//    }

    private Map<String, Object> putUserAttributeToSession() {
        Map<String, Object> sessionAttribute = new HashMap<>();
        sessionAttribute.put(USER_ID_PARAMETER, 1);
        sessionAttribute.put(FILM_PARAMETER, FILM);
        return sessionAttribute;
    }
}
