package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.film.FilmService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class DeleteFilmCommandTest {
    private static final String FILM_PARAMETER = "film";

    private static final int FILM_ID_VALUE = 1;
    private static final String TITLE_VALUE = "Valid Title";
    private static final String DIRECTOR_VALUE = "Valid Director";
    private static final String POSTER_VALUE = "Valid Poster";
    private static final int DEFAULT_RATE_VALUE = 10;
    private static final Film FILM = new Film(FILM_ID_VALUE, TITLE_VALUE, DIRECTOR_VALUE,
            POSTER_VALUE, DEFAULT_RATE_VALUE);
    private static final String INDEX_PAGE = "index.jsp";

    @Test
    public void testExecuteShouldReturnRedirectWhenDeletingIsSuccessful() throws ServiceException {
        //given
        //when
        FilmService filmService = Mockito.mock(FilmService.class);
        Map<String, Object> sessionAttribute = putFilmAttributeToSession();
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), sessionAttribute);
        DeleteFilmCommand deleteFilm = new DeleteFilmCommand(filmService);
        CommandResult actual = deleteFilm.execute(context);
        //then
        CommandResult expected = CommandResult.redirect(INDEX_PAGE);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ServiceException.class)
    public void testExecuteShouldThrowServiceExceptionWhenFilmParameterEqualsNull() throws ServiceException {
        //given
        FilmService filmService = Mockito.mock(FilmService.class);
        Map<String, String> requestParameters = new HashMap<>();
        //when
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        DeleteFilmCommand deleteFilm = new DeleteFilmCommand(filmService);
        //then
        deleteFilm.execute(context);
    }

    private Map<String, Object> putFilmAttributeToSession() {
        Map<String, Object> sessionAttribute = new HashMap<>();
        sessionAttribute.put(FILM_PARAMETER, FILM);
        return sessionAttribute;
    }
}
