package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.film.FilmServiceImpl;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.List;

public class SortFilm implements Command {
    private final FilmServiceImpl filmServiceImpl;
    private static final String FILMS = "films";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";

    public SortFilm(FilmServiceImpl filmServiceImpl) {
        this.filmServiceImpl = filmServiceImpl;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        List<Film> films = filmServiceImpl.getAll();
        requestContext.setRequestAttribute(FILMS, films);
        return CommandResult.forward(FILMS_PAGE);
    }
}
