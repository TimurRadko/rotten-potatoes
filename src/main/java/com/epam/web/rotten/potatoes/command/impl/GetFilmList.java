package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionServiceImpl;
import com.epam.web.rotten.potatoes.service.film.FilmServiceImpl;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.ArrayList;
import java.util.List;

public class GetFilmList implements Command {
    private static final String FILMS = "films";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";
    private final FilmServiceImpl filmServiceImpl;
    private final UserActionServiceImpl userActionService;

    private static final int FIRST_PAGE = 1;
    private static final int FILMS_PER_PAGE = 5;
    private static final String CURRENT_PAGE = "currentPage";


    public GetFilmList(FilmServiceImpl filmServiceImpl, UserActionServiceImpl userActionService) {
        this.filmServiceImpl = filmServiceImpl;
        this.userActionService = userActionService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String stringCurrentPage = requestContext.getRequestParameter(CURRENT_PAGE);
        int currentPage;
        if (stringCurrentPage == null) {
            currentPage = FIRST_PAGE;
        } else {
            currentPage = Integer.parseInt(stringCurrentPage);
        }
        List<Film> films = filmServiceImpl.getFilmsPart((currentPage - 1) * FILMS_PER_PAGE, FILMS_PER_PAGE);
        List<Film> newAvgRateFilms = getNewRateFilms(films);
        requestContext.setRequestAttribute(FILMS, newAvgRateFilms);
        requestContext.setRequestAttribute(CURRENT_PAGE, currentPage);
        return CommandResult.forward(FILMS_PAGE);
    }

    private List<Film> getNewRateFilms(List<Film> films) throws ServiceException {
        GetFilmById getFilmById = new GetFilmById(filmServiceImpl, userActionService);
        List<Film> newAvgRateFilms = new ArrayList<>();
        for (Film film : films) {
            Film newRateFilm = getFilmById.getFilmWithNewRate(film);
            newAvgRateFilms.add(newRateFilm);
        }
        return newAvgRateFilms;
    }
}
