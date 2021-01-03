package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import com.epam.web.rotten.potatoes.service.film.FilmService;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.List;
import java.util.Optional;

public class GetFilmById implements Command {
    private final FilmService filmService;
    private static final String ID_PARAMETER = "id";
    private static final String FILM_HOME_PAGE = "WEB-INF/views/film-home.jsp";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";
    private static final String FILM = "film";

    public GetFilmById(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String stringFilmId = requestContext.getRequestParameter(ID_PARAMETER);
        int filmId = Integer.parseInt(stringFilmId);
        Optional<Film> optionalFilm = filmService.getFilmById(filmId);
        if (optionalFilm.isPresent()) {
            Film film = optionalFilm.get();
            requestContext.setSessionAttribute(FILM, film);
            return CommandResult.forward(FILM_HOME_PAGE);
        } else {
            return CommandResult.forward(FILMS_PAGE);
        }
    }
}