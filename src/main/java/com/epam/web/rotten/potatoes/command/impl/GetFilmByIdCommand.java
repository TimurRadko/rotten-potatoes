package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import com.epam.web.rotten.potatoes.service.film.FilmService;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class GetFilmByIdCommand implements Command {
    private final FilmService filmService;
    private final UserActionService userActionService;
    private static final String ID_PARAMETER = "id";
    private static final String FILM_HOME_PAGE = "WEB-INF/views/film-home.jsp";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";
    private static final String FILM = "film";

    public GetFilmByIdCommand(FilmService filmService, UserActionService userActionService) {
        this.filmService = filmService;
        this.userActionService = userActionService;
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
            Film newFilm = getFilmWithCurrentAvgRate(film);
            requestContext.setSessionAttribute(FILM, newFilm);
            return CommandResult.forward(FILM_HOME_PAGE);
        } else {
            return CommandResult.forward(FILMS_PAGE);
        }
    }

    /*package-private*/ Film getFilmWithCurrentAvgRate(Film film) throws ServiceException {
        int id = film.getId();
        String title = film.getTitle();
        String director = film.getDirector();
        String poster = film.getPoster();
        double defaultAvgRate = film.getDefaultRate();
        double currentAvgRate = getCurrentAvgRate(id, defaultAvgRate);
        currentAvgRate = round(currentAvgRate);
        return new Film(id, title, director, poster, currentAvgRate);
    }

    private static double round(double value) {
        BigDecimal bigDecimalNumber = new BigDecimal(Double.toString(value));
        bigDecimalNumber = bigDecimalNumber.setScale(1, RoundingMode.HALF_UP);
        return bigDecimalNumber.doubleValue();
    }

    private double getCurrentAvgRate(int id, double defaultAvgRate) throws ServiceException {
        List<UserAction> userActions = userActionService.getReviewsByFilmId(id);
        double sumAvgRate = 0;
        for (UserAction userAction : userActions) {
            sumAvgRate += userAction.getFilmRate();
        }
        if (sumAvgRate > 0) {
            double resultAvgRate = sumAvgRate / userActions.size();
            return (resultAvgRate + defaultAvgRate) / 2;
        } else {
            return defaultAvgRate;
        }
    }
}