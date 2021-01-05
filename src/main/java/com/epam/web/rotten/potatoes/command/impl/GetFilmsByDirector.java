package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.film.FilmService;

import java.util.List;

public class GetFilmsByDirector implements Command {
    private final FilmService filmService;
    private static final String DIRECTOR_PARAMETER = "director";
    private static final String FILMS = "films";
    private static final String DIRECTORS_PAGE = "WEB-INF/views/director.jsp";

    public GetFilmsByDirector(FilmService filmServiceImpl) {
        this.filmService = filmServiceImpl;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String director = requestContext.getRequestParameter(DIRECTOR_PARAMETER);
        List<Film> films = filmService.getFilmByDirector(director);
        requestContext.setRequestAttribute(FILMS, films);
        requestContext.setRequestAttribute(DIRECTOR_PARAMETER, director);
        return CommandResult.forward(DIRECTORS_PAGE);
    }
}
