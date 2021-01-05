package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.PageNotFoundException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.film.FilmService;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.List;

public class GetFilmList implements Command {
    private static final String FILMS = "films";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";
    private final FilmService filmService;

    private static final int FIRST_PAGE = 1;
    private static final int FILMS_PER_PAGE = 5;
    private static final String CURRENT_PAGE = "currentPage";
    private static final String EXCEPTION = "Current Page doesn't Exist";

    public GetFilmList(FilmService filmService) {
        this.filmService = filmService;
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
        requestContext.setRequestAttribute(FILMS, films);
        requestContext.setRequestAttribute(CURRENT_PAGE, currentPage);
        return CommandResult.forward(FILMS_PAGE);
    }
}
