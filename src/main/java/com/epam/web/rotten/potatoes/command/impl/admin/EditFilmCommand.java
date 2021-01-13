package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.film.FilmService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class EditFilmCommand implements Command {
    private final FilmService filmService;
    private static final String FILM = "film";
    private static final String TITLE_PARAMETER = "title";
    private static final String POSTER_PATH = "static/images/";
    private static final String POSTER_PATH_PARAMETER = "poster-path";
    private static final String DIRECTOR_PARAMETER = "director";
    private static final String REQUEST_ATTRIBUTE = "req";
    private static final String EMPTY_PARAMETER = "";
    private static final int MAX_LENGTH = 100;

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_EMPTY_DATA = "errorEmptyData";
    private static final String ERROR_LONG_DATA = "errorLongData";
    private static final String FILM_HOME_PAGE_COMMAND = "/rotten-potatoes/controller?command=film-home&id=";
    private static final String FILM_ADD_PAGE = "WEB-INF/views/film-add.jsp";

    public EditFilmCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        Film film = (Film) requestContext.getSessionAttribute(FILM);
        int filmId = film.getId();
        String currentPoster = film.getPoster();
        double defaultAvgRate = film.getDefaultRate();

        String title = requestContext.getRequestParameter(TITLE_PARAMETER);
        String director = requestContext.getRequestParameter(DIRECTOR_PARAMETER);
        HttpServletRequest req = (HttpServletRequest) requestContext.getRequestAttribute(REQUEST_ATTRIBUTE);
        ServletContext servletContext = req.getServletContext();
        Part newPoster;
        try {
            newPoster = req.getPart(POSTER_PATH_PARAMETER);
        } catch (IOException | ServletException e) {
            throw new ServiceException(e);
        }
        if (title.equals(EMPTY_PARAMETER) || director.equals(EMPTY_PARAMETER) || newPoster == null) {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_EMPTY_DATA);
            return CommandResult.forward(FILM_HOME_PAGE_COMMAND + filmId);
        }

        if (title.length() > MAX_LENGTH || director.length() > MAX_LENGTH) {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_LONG_DATA);
            return CommandResult.forward(FILM_ADD_PAGE);
        }

        if (newPoster.getSize() > 0) {
            String newPosterName = newPoster.getSubmittedFileName();
            if (!currentPoster.equals(newPosterName)) {
                UUID uuid = UUID.randomUUID();
                currentPoster = POSTER_PATH + uuid;
            }

            String applicationPath = servletContext.getRealPath("");
            Path path = Paths.get(applicationPath, currentPoster);

            try (InputStream inputStream = newPoster.getInputStream()) {
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new ServiceException(e);
            }
        }
        film = new Film(filmId, title, director, currentPoster, defaultAvgRate);
        filmService.save(film);
        return CommandResult.redirect(FILM_HOME_PAGE_COMMAND + filmId);
    }
}