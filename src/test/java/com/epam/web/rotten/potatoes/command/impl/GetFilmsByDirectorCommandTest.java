package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.service.film.FilmService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class GetFilmsByDirectorCommandTest {
    private static final String DIRECTOR_PARAMETER = "director";
    private static final String DIRECTOR_VALUE = "Valid Director";
    private static final String DIRECTORS_PAGE = "WEB-INF/views/director.jsp";

    @Test
    public void testExecuteReturnForwardDirectorsPageWhenDirectorParameterNotNull() throws ServiceException {
        FilmService filmService = Mockito.mock(FilmService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(DIRECTOR_PARAMETER, DIRECTOR_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        GetFilmsByDirectorCommand getFilmsByDirector = new GetFilmsByDirectorCommand(filmService);
        CommandResult actual = getFilmsByDirector.execute(context);

        CommandResult expected = CommandResult.forward(DIRECTORS_PAGE);
        Assert.assertEquals(expected, actual);
    }
}
