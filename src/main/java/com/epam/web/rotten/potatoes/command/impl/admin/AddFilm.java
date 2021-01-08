package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.film.FilmService;
import com.epam.web.rotten.potatoes.validator.Validator;

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
import java.util.Optional;
import java.util.UUID;

public class AddFilm implements Command {
    private final FilmService filmService;
    private final Validator<Film> filmValidator;
    private static final String TITLE_PARAMETER = "title";
    private static final String POSTER_PATH = "static/images/";
    private static final String POSTER_PARAMETER = "poster-path";
    private static final String DIRECTOR_PARAMETER = "director";
    private static final String REQUEST_ATTRIBUTE = "req";
    private static final double DEFAULT_RATE = 0;
    private static final String EMPTY_PARAMETER = "";

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_EMPTY_DATA = "errorEmptyData";
    private static final String INVALID_FILM_DATA = "invalidFilmData";
    private static final String FILM_ADD_PAGE = "WEB-INF/views/film-add.jsp";
    private static final String FILM_HOME_PAGE_COMMAND = "/controller?command=film-home&id=";

    public AddFilm(FilmService filmService, Validator<Film> filmValidator) {
        this.filmService = filmService;
        this.filmValidator = filmValidator;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String title = requestContext.getRequestParameter(TITLE_PARAMETER);
        String director = requestContext.getRequestParameter(DIRECTOR_PARAMETER);
        HttpServletRequest req = (HttpServletRequest) requestContext.getRequestAttribute(REQUEST_ATTRIBUTE);
        ServletContext servletContext = req.getServletContext();
        Part newPoster;
        try {
            newPoster = req.getPart(POSTER_PARAMETER);
        } catch (IOException | ServletException e) {
            throw new ServiceException(e);
        }

        String newPosterName = newPoster.getSubmittedFileName();
        if (title.equals(EMPTY_PARAMETER) || director.equals(EMPTY_PARAMETER) || newPosterName.equals(EMPTY_PARAMETER)) {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_EMPTY_DATA);
            return CommandResult.forward(FILM_ADD_PAGE);
        }

        if (newPoster.getSize() > 0) {
            UUID uuid = UUID.randomUUID();
            String posterPath = POSTER_PATH + uuid;
            String applicationPath = servletContext.getRealPath("");
            Path path = Paths.get(applicationPath, posterPath);
            try (InputStream inputStream = newPoster.getInputStream()) {
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new ServiceException(e);
            }
            Film film = new Film(null, title, director, posterPath, DEFAULT_RATE);
            if (filmValidator.isValid(film)) {
                Optional<Integer> optionalId = filmService.save(film);
                if (optionalId.isPresent()) {
                    int id = optionalId.get();
                    return CommandResult.forward(FILM_HOME_PAGE_COMMAND + id);
                }
            } else {
                requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, INVALID_FILM_DATA);
                return CommandResult.forward(FILM_ADD_PAGE);
            }
        }
        return CommandResult.forward(FILM_ADD_PAGE);
    }
}
