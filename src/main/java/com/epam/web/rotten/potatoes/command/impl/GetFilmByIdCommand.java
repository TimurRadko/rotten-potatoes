package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import com.epam.web.rotten.potatoes.service.film.FilmService;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.Optional;

public class GetFilmByIdCommand extends AbstractFilmCommand implements Command {
    private final FilmService filmService;
    private static final String ID_PARAMETER = "id";
    private static final String FILM_HOME_PAGE = "WEB-INF/views/film-home.jsp";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";
    private static final String FILM = "film";

    public GetFilmByIdCommand(FilmService filmService, UserActionService userActionService) {
        super(userActionService);
        this.filmService = filmService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String stringFilmId = requestContext.getRequestParameter(ID_PARAMETER);
        if (stringFilmId == null) {
            throw new ServiceException("Incoming parameters are: null");
        }
        int filmId = Integer.parseInt(stringFilmId);
        Optional<Film> optionalFilm = filmService.getFilmById(filmId);
        if (optionalFilm.isPresent()) {
            Film film = optionalFilm.get();
            double currentAvgRate = getCurrentAvgRate(film);
            currentAvgRate = round(currentAvgRate);
            Film newFilm = new Film(film, currentAvgRate);
            requestContext.setSessionAttribute(FILM, newFilm);
            return CommandResult.forward(FILM_HOME_PAGE);
        } else {
            return CommandResult.forward(FILMS_PAGE);
        }
    }
}