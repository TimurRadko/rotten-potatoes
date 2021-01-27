package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import com.epam.web.rotten.potatoes.service.film.FilmService;

import java.util.ArrayList;
import java.util.List;

public class GetFilmsByDirectorCommand extends AbstractFilmCommand implements Command {
    private final FilmService filmService;
    private static final String DIRECTOR_PARAMETER = "director";
    private static final String FILMS = "films";
    private static final String DIRECTORS_PAGE = "WEB-INF/views/director.jsp";

    public GetFilmsByDirectorCommand(FilmService filmServiceImpl, UserActionService userActionService) {
        super(userActionService);
        this.filmService = filmServiceImpl;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String director = requestContext.getRequestParameter(DIRECTOR_PARAMETER);
        List<Film> films = filmService.getFilmByDirector(director);
        List<Film> newCurrentAvrRateFilms = getFilmsWithNewAvgRate(films);
        requestContext.setRequestAttribute(FILMS, newCurrentAvrRateFilms);
        requestContext.setRequestAttribute(DIRECTOR_PARAMETER, director);
        return CommandResult.forward(DIRECTORS_PAGE);
    }

    private List<Film> getFilmsWithNewAvgRate(List<Film> films) throws ServiceException {
        List<Film> newAvgRateFilms = new ArrayList<>();
        for (Film film : films) {
            double currentAvgRate = getCurrentAvgRate(film);
            currentAvgRate = round(currentAvgRate);
            Film newFilm = new Film(film, currentAvgRate);
            newAvgRateFilms.add(newFilm);
        }
        return  newAvgRateFilms;
    }
}