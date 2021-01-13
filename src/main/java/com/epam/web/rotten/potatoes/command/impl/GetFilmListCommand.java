package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.PageNotFoundException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.action.UserActionService;
import com.epam.web.rotten.potatoes.service.film.FilmService;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.ArrayList;
import java.util.List;

public class GetFilmListCommand implements Command {
    private static final String FILMS = "films";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";
    private final FilmService filmService;
    private final UserActionService userActionService;

    private static final int FIRST_PAGE = 1;
    private static final int FILMS_PER_PAGE = 5;
    private static final String CURRENT_PAGE = "currentPage";
    private static final String EXCEPTION = "Current Page doesn't Exist";

    public GetFilmListCommand(FilmService filmService, UserActionService userActionService) {
        this.filmService = filmService;
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

        int numberOfPages = filmService.getCountRows(FILMS_PER_PAGE);
        if (currentPage > numberOfPages) {
            throw new PageNotFoundException(EXCEPTION);
        }
        List<Film> films = filmService.getPage(currentPage, FILMS_PER_PAGE);
        List<Film> newCurrentAvrRateFilms = getFilmsWithNewAvgRate(films);
        requestContext.setRequestAttribute(FILMS, newCurrentAvrRateFilms);
        requestContext.setRequestAttribute(CURRENT_PAGE, currentPage);
        return CommandResult.forward(FILMS_PAGE);
    }

    private List<Film> getFilmsWithNewAvgRate(List<Film> films) throws ServiceException {
        GetFilmByIdCommand getFilmById = new GetFilmByIdCommand(filmService, userActionService);
        List<Film> newAvgRateFilms = new ArrayList<>();
        for (Film film : films) {
            Film newAvgRateFilm = getFilmById.getFilmWithCurrentAvgRate(film);
            newAvgRateFilms.add(newAvgRateFilm);
        }
        return  newAvgRateFilms;
    }
}
