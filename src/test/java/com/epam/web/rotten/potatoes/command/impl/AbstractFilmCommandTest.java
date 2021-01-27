package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import com.epam.web.rotten.potatoes.service.film.FilmService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class AbstractFilmCommandTest {
    private static final int FILM_ID_VALUE = 1;
    private static final String TITLE_VALUE = "Valid Title";
    private static final String DIRECTOR_VALUE = "Valid Director";
    private static final String POSTER_VALUE = "Valid Poster";
    private static final int DEFAULT_RATE_VALUE = 10;
    private static final Film FILM = new Film(FILM_ID_VALUE, TITLE_VALUE, DIRECTOR_VALUE,
            POSTER_VALUE, DEFAULT_RATE_VALUE);

    private static final double DELTA = 0.1;
    private static final double FIRST_EXPECTED_AVG_RATE = 10;
    private static final double SECOND_EXPECTED_AVG_RATE = 7.5;

    private static final int USER_ID_VALUE = 1;
    private static final int USER_ACTION_ID_VALUE = 1;
    private static final int FILM_RATE_VALUE = 5;
    private static final String REVIEW_VALUE = "Hello";
    private static final UserAction USER_ACTION =
            new UserAction(USER_ACTION_ID_VALUE, FILM_RATE_VALUE, REVIEW_VALUE, USER_ID_VALUE, FILM_ID_VALUE);
    private static final List<UserAction> USER_ACTIONS = Collections.singletonList(USER_ACTION);

    @Test
    public void testCurrentAvgRateShouldReturnDefaultRateWhenUserActionTableEmpty() throws ServiceException {
        //given
        FilmService filmService = Mockito.mock(FilmService.class);
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        AbstractFilmCommand abstractFilmCommand = new GetFilmByIdCommand(filmService, userActionService);

        //when
        //then
        double actualAvgRate = abstractFilmCommand.getCurrentAvgRate(FILM);
        Assert.assertEquals(FIRST_EXPECTED_AVG_RATE, actualAvgRate, DELTA);
    }

    @Test
    public void testCurrentAvgRateShouldReturnDefaultRateWhenUserActionTableNotEmpty() throws ServiceException {
        //given
        FilmService filmService = Mockito.mock(FilmService.class);
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        AbstractFilmCommand abstractFilmCommand = new GetFilmByIdCommand(filmService, userActionService);

        //when
        when(userActionService.getReviewsByFilmId(anyInt())).thenReturn(USER_ACTIONS);
        double actualAvgRate = abstractFilmCommand.getCurrentAvgRate(FILM);

        //then
        Assert.assertEquals(SECOND_EXPECTED_AVG_RATE, actualAvgRate, DELTA);
    }
}
