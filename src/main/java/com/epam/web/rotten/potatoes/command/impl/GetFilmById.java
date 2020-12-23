package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.film.FilmService;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.Optional;

public class GetFilmById implements Command {
    private final FilmService filmService;
    private static final String ID_PARAMETER = "id";
    private static final String FILM_HOME_PAGE = "WEB-INF/views/film-home.jsp";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";

    private static final String FILM_ID_PARAMETER = "film_id";
    private static final String TITLE_PARAMETER = "title";
    private static final String DIRECTOR_PARAMETER = "director";
    private static final String POSTER_PARAMETER = "poster";
    private static final String AVG_RATE_PARAMETER = "avgRate";

    public GetFilmById(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int id = Integer.parseInt(requestContext.getRequestParameter(ID_PARAMETER));
        Optional<Film> film = filmService.getFilmById(id);
        if (film.isPresent()) {
            Film sessionFilm = film.get();
            setSessionFilmData(requestContext, sessionFilm);
            return CommandResult.forward(FILM_HOME_PAGE);
        } else {
            return CommandResult.forward(FILMS_PAGE);
        }
    }

    private void setSessionFilmData(RequestContext requestContext, Film sessionFilm) {
        int id = sessionFilm.getId();
        String title = sessionFilm.getTitle();
        String director = sessionFilm.getDirector();
        String poster = sessionFilm.getPoster();
        double avgRate = sessionFilm.getAvgRate();
        requestContext.setSessionAttribute(FILM_ID_PARAMETER, id);
        requestContext.setSessionAttribute(TITLE_PARAMETER, title);
        requestContext.setSessionAttribute(DIRECTOR_PARAMETER, director);
        requestContext.setSessionAttribute(POSTER_PARAMETER, poster);
        requestContext.setSessionAttribute(AVG_RATE_PARAMETER, avgRate);
    }
}
