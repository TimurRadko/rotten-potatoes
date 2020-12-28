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
    private final UserActionService userActionService;
    private static final String ID_PARAMETER = "id";
    private static final String FILM_HOME_PAGE = "WEB-INF/views/film-home.jsp";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";
    private static final String FILM = "film";

    public GetFilmById(FilmService filmService, UserActionService userActionService) {
        this.filmService = filmService;
        this.userActionService = userActionService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String stringFilmId = requestContext.getRequestParameter(ID_PARAMETER);
        int filmId = Integer.parseInt(stringFilmId);
        Optional<Film> optionalFilm = filmService.getFilmById(filmId);
        if (optionalFilm.isPresent()) {
            Film film = optionalFilm.get();
            requestContext.setSessionAttribute(FILM, getFilmWithNewRate(film));
            return CommandResult.forward(FILM_HOME_PAGE);
        } else {
            return CommandResult.forward(FILMS_PAGE);
        }
    }

    /*private-package*/ Film getFilmWithNewRate(Film film) throws ServiceException {
        int filmId = film.getId();
        String title = film.getTitle();
        String director = film.getDirector();
        String poster = film.getPoster();
        double currentAvgRate = film.getAvgRate();

        List<UserAction> actions = userActionService.findReviewsByFilmId(filmId);
        if (actions.size() > 0) {
            double avgUserActionAvgRate = 0;
            for (UserAction action : actions) {
                avgUserActionAvgRate += action.getFilmRate();
            }
            avgUserActionAvgRate = avgUserActionAvgRate / actions.size();
            double resultAvgRate = (currentAvgRate + avgUserActionAvgRate) / 2;
            return new Film(filmId, title, director, poster, resultAvgRate);
        } else {
            return new Film(filmId, title, director, poster, currentAvgRate);
        }
    }
}