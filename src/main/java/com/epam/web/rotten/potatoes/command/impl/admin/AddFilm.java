package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.service.film.FilmServiceImpl;

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
    private final FilmServiceImpl filmService;
    private static final String TITLE_PARAMETER = "title";
    private static final String POSTER_PATH = "static/images/";
    private static final String POSTER_PARAMETER = "poster-path";
    private static final String DIRECTOR_PARAMETER = "director";
    private static final double DEFAULT_AVG_RATE = 0;

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_EMPTY_DATA = "errorEmptyData";
    private static final String ADD_FILM_PAGE = "WEB-INF/views/film-save.jsp";
    private static final String FILM_HOME_PAGE = "/controller?command=film-home&id=";

    public AddFilm(FilmServiceImpl filmService) {
        this.filmService = filmService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String title = requestContext.getRequestParameter(TITLE_PARAMETER);
        String director = requestContext.getRequestParameter(DIRECTOR_PARAMETER);
        HttpServletRequest req = requestContext.getReq();
        ServletContext servletContext = req.getServletContext();
        Part poster;
        try {
            poster = req.getPart(POSTER_PARAMETER);
        } catch (IOException | ServletException e) {
            throw new ServiceException(e);
        }

        if (title == null || director == null || poster == null) {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_EMPTY_DATA);
            return CommandResult.forward(ADD_FILM_PAGE);
        }

        if (poster.getSize() > 0) {
            UUID uuid = UUID.randomUUID();
            String posterPath = POSTER_PATH + uuid;

            String applicationPath = servletContext.getRealPath("");
            Path path = Paths.get(applicationPath, posterPath);

            try (InputStream inputStream = poster.getInputStream()) {
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new ServiceException(e);
            }

            Film film = new Film(null, title, director, posterPath, DEFAULT_AVG_RATE);
            Optional<Integer> optionalId = filmService.saveFilm(film);

            if (optionalId.isPresent()) {
                int id = optionalId.get();
                return CommandResult.forward(FILM_HOME_PAGE + id);
            }
        }
        return CommandResult.forward(ADD_FILM_PAGE);
    }
}
