package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.film.FilmServiceImpl;

import java.util.List;

public class GetFilmsByDirector implements Command {
    private final FilmServiceImpl filmServiceImpl;
    private static final String DIRECTOR_PARAMETER = "director";
    private static final String FILMS = "films";
    private static final String DIRECTORS_PAGE = "WEB-INF/views/director.jsp";

    public GetFilmsByDirector(FilmServiceImpl filmServiceImpl) {
        this.filmServiceImpl = filmServiceImpl;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String director = requestContext.getRequestParameter(DIRECTOR_PARAMETER);
        List<Film> films = filmServiceImpl.getFilmByDirector(director);
        requestContext.setRequestAttribute(FILMS, films);
        requestContext.setSessionAttribute(DIRECTOR_PARAMETER, director);
        return CommandResult.forward(DIRECTORS_PAGE);
    }
}
