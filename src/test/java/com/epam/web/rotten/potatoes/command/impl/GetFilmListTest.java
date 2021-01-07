package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.PageNotFoundException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import com.epam.web.rotten.potatoes.service.film.FilmService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class GetFilmListTest {
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";
    private static final String CURRENT_PAGE_PARAMETER = "currentPage";
    private static final String VALID_VALUE_CURRENT_PAGE = "2";
    private static final int CURRENT_PAGE = 2;
    private static final int FILMS_PER_PAGE = 5;
    private static final int VALID_COUNT_ROWS = 3;
    private static final int INVALID_COUNT_ROWS = 1;

    @Test
    public void testExecuteShouldReturnForwardFilmsPageWhenCurrentPageIntoBorders() throws ServiceException {
        FilmService filmService = Mockito.mock(FilmService.class);
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(CURRENT_PAGE_PARAMETER, VALID_VALUE_CURRENT_PAGE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        filmService.getPage(CURRENT_PAGE, FILMS_PER_PAGE);
        GetFilmList getFilmList = new GetFilmList(filmService, userActionService);

        when(filmService.getCountRows(FILMS_PER_PAGE)).thenReturn(VALID_COUNT_ROWS);

        CommandResult expected = CommandResult.forward(FILMS_PAGE);

        CommandResult actual = getFilmList.execute(context);
        Assert.assertEquals(actual, expected);
    }

    @Test(expected = PageNotFoundException.class)
    public void testExecuteShouldThrowExceptionWhenCurrentPageNotInBorders() throws ServiceException {
        FilmService filmService = Mockito.mock(FilmService.class);
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(CURRENT_PAGE_PARAMETER, VALID_VALUE_CURRENT_PAGE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        filmService.getPage(CURRENT_PAGE, FILMS_PER_PAGE);
        GetFilmList getFilmList = new GetFilmList(filmService, userActionService);

        when(filmService.getCountRows(FILMS_PER_PAGE)).thenReturn(INVALID_COUNT_ROWS);
        getFilmList.execute(context);
    }
}
