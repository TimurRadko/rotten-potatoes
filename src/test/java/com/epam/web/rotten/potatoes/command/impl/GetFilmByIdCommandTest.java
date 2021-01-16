package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.command.impl.both.GetUserByIdCommand;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import com.epam.web.rotten.potatoes.service.film.FilmService;
import com.epam.web.rotten.potatoes.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class GetFilmByIdCommandTest {
    private static final String ID_PARAMETER = "id";
    private static final String ID_VALUE = "1";
    private static final String INVALID_ID_VALUE = "2";

    private static final int FILM_ID_VALUE = 1;
    private static final String TITLE_VALUE = "Valid Title";
    private static final String DIRECTOR_VALUE = "Valid Director";
    private static final String POSTER_VALUE = "Valid Poster";
    private static final int DEFAULT_RATE_VALUE = 10;
    private static final Film FILM = new Film(FILM_ID_VALUE, TITLE_VALUE, DIRECTOR_VALUE,
            POSTER_VALUE, DEFAULT_RATE_VALUE);

    private static final String FILM_HOME_PAGE = "WEB-INF/views/film-home.jsp";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";

    @Test
    public void testExecuteShouldReturnForwardFilmHomePageWhenFilmFound() throws ServiceException {
        FilmService filmService = Mockito.mock(FilmService.class);
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, ID_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        GetFilmByIdCommand getFilmById = new GetFilmByIdCommand(filmService, userActionService);
        Optional<Film> optionalFilm = Optional.of(FILM);
        when(filmService.getFilmById(anyInt())).thenReturn(optionalFilm);

        CommandResult actual = getFilmById.execute(context);

        CommandResult expected = CommandResult.forward(FILM_HOME_PAGE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecuteShouldReturnForwardFilmsPageWhenFilmNotFound() throws ServiceException {
        UserService userService = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, INVALID_ID_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        GetUserByIdCommand getUserById = new GetUserByIdCommand(userService);
        Optional<User> userOptional = Optional.empty();
        when(userService.getUserById(anyInt())).thenReturn(userOptional);

        CommandResult actual = getUserById.execute(context);

        CommandResult expected = CommandResult.forward(FILMS_PAGE);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ServiceException.class)
    public void testExecuteShouldThrowServiceExceptionWhenFilmParameterEqualsNull() throws ServiceException {
        FilmService filmService = Mockito.mock(FilmService.class);
        UserActionService userActionService = Mockito.mock(UserActionService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, null);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        GetFilmByIdCommand getFilmById = new GetFilmByIdCommand(filmService, userActionService);
        getFilmById.execute(context);
    }
}