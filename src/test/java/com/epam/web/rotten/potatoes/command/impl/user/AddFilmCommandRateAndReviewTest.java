package com.epam.web.rotten.potatoes.command.impl.user;

import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class AddFilmCommandRateAndReviewTest {
    private static final int FILM_ID_VALUE = 1;
    private static final String TITLE_VALUE = "Valid Title";
    private static final String DIRECTOR_VALUE = "Valid Director";
    private static final String POSTER_VALUE = "Valid Poster";
    private static final int DEFAULT_RATE_VALUE = 10;
    private static final Film FILM = new Film(FILM_ID_VALUE, TITLE_VALUE, DIRECTOR_VALUE,
            POSTER_VALUE, DEFAULT_RATE_VALUE);

    private static final int USER_ID_VALUE = 1;
    private static final String LOGIN_VALUE = "Valid Login";
    private static final String RIGHTS_VALUE = "user";
    private static final int RATE_VALUE = 10;
    private static final User USER = new User(USER_ID_VALUE, LOGIN_VALUE, RIGHTS_VALUE, RATE_VALUE, false);

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_EMPTY_REVIEW = "errorEmptyReview";
    private static final String ERROR_REPEATED_REVIEW = "errorRepeatedReview";
    private static final String ERROR_LONG_REVIEW = "errorLongReview";
    private static final String REVIEW_PARAMETER = "review";
    private static final String USER_PARAMETER = "user";
    private static final String FILM_PARAMETER = "film";
    private static final String REVIEW_VALUE = "Hello";
    private static final String EMPTY_REVIEW_VALUE = "";

    private static final int USER_ACTION_ID_VALUE = 1;
    private static final int FILM_RATE_VALUE = 10;
    private static final UserAction USER_ACTION =
            new UserAction(USER_ACTION_ID_VALUE, FILM_RATE_VALUE, REVIEW_VALUE, USER_ID_VALUE, FILM_ID_VALUE);

    private static final List<UserAction> USER_ACTIONS = Collections.singletonList(USER_ACTION);
    private static final String LONG_REVIEW_VALUE = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
            "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
            "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. " +
            "Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. " +
            "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis " +
            "pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend " +
            "tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, " +
            "dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. " +
            "Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies " +
            "nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper " +
            "libero, sit amet adipiscing sem neque sed ipsum. N\n";
    private static final String FILM_RATE_PARAMETER = "film_rate";
    private static final String FILM_RATE = "5";
    private static final String INVALID_FILM_RATE = "-5";
    private static final String ERROR_RATE = "errorRate";
    private static final String FILM_HOME_PAGE_COMMAND = "/rotten-potatoes/controller?command=film-home&id=1";

    @Test
    public void testExecuteShouldPutToContextErrorMessageWhenReviewEmpty() throws ServiceException {
        //given
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(REVIEW_PARAMETER, EMPTY_REVIEW_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        AddFilmRateAndReviewCommand addFilmRateAndReview = new AddFilmRateAndReviewCommand(userActionService);
        //when
        addFilmRateAndReview.execute(context);
        //then
        String actualError = (String) context.getRequestAttribute(ERROR_MESSAGE_ATTRIBUTE);
        Assert.assertEquals(ERROR_EMPTY_REVIEW, actualError);
    }

    @Test
    public void testExecuteShouldPutToContextErrorMessageWhenUserHasAlreadyWrittenReview() throws ServiceException {
        //given
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(REVIEW_PARAMETER, REVIEW_VALUE);
        requestParameters.put(FILM_RATE_PARAMETER, FILM_RATE);
        Map<String, Object> sessionAttribute = putUserAttributeToSession();
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, sessionAttribute);
        //when
        when(userActionService.getReviewsByUserId(anyInt())).thenReturn(USER_ACTIONS);
        AddFilmRateAndReviewCommand addFilmRateAndReview = new AddFilmRateAndReviewCommand(userActionService);
        addFilmRateAndReview.execute(context);
        //then
        String actualError = (String) context.getRequestAttribute(ERROR_MESSAGE_ATTRIBUTE);
        Assert.assertEquals(ERROR_REPEATED_REVIEW, actualError);
    }

    @Test
    public void testExecuteShouldPutToContextErrorMessageWhenUserWroteLongReview() throws ServiceException {
        //given
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(REVIEW_PARAMETER, LONG_REVIEW_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        AddFilmRateAndReviewCommand addFilmRateAndReview = new AddFilmRateAndReviewCommand(userActionService);
        //when
        addFilmRateAndReview.execute(context);
        //then
        String actualError = (String) context.getRequestAttribute(ERROR_MESSAGE_ATTRIBUTE);
        Assert.assertEquals(ERROR_LONG_REVIEW, actualError);
    }

    @Test
    public void testExecuteShouldPutToContextErrorMessageWhenUserSendInvalidRate() throws ServiceException {
        //given
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(REVIEW_PARAMETER, REVIEW_VALUE);
        requestParameters.put(FILM_RATE_PARAMETER, INVALID_FILM_RATE);
        Map<String, Object> sessionAttribute = putUserAttributeToSession();
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, sessionAttribute);
        AddFilmRateAndReviewCommand addFilmRateAndReview = new AddFilmRateAndReviewCommand(userActionService);
        //when
        addFilmRateAndReview.execute(context);
        //then
        String actualError = (String) context.getRequestAttribute(ERROR_MESSAGE_ATTRIBUTE);
        Assert.assertEquals(ERROR_RATE, actualError);
    }

    @Test
    public void testExecuteShouldReturnRedirectWhenUserActionIsValid() throws ServiceException {
        //given
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(REVIEW_PARAMETER, REVIEW_VALUE);
        requestParameters.put(FILM_RATE_PARAMETER, FILM_RATE);
        Map<String, Object> sessionAttribute = putUserAttributeToSession();
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, sessionAttribute);
        AddFilmRateAndReviewCommand addFilmRateAndReview = new AddFilmRateAndReviewCommand(userActionService);
        Optional<UserAction> optionalUserAction = Optional.of(USER_ACTION);
        //when
        when(userActionService.getReviewById(anyInt())).thenReturn(optionalUserAction);
        CommandResult actual = addFilmRateAndReview.execute(context);
        //then
        CommandResult expected = CommandResult.redirect(FILM_HOME_PAGE_COMMAND);
        Assert.assertEquals(expected, actual);
    }

    private Map<String, Object> putUserAttributeToSession() {
        Map<String, Object> sessionAttribute = new HashMap<>();
        sessionAttribute.put(USER_PARAMETER, USER);
        sessionAttribute.put(FILM_PARAMETER, FILM);
        return sessionAttribute;
    }
}