package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.film.FilmService;

public class DeleteFilm implements Command {
    private final FilmService filmService;
    private static final String FILM = "film";
    private static final String INDEX_PAGE = "index.jsp";

    public DeleteFilm(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        Film film  = (Film) requestContext.getSessionAttribute(FILM);
        int filmId = film.getId();
        filmService.remove(filmId);
        return CommandResult.redirect(INDEX_PAGE);
    }
}
