package com.epam.web.rotten.potatoes.dao.impl.film;

import com.epam.web.rotten.potatoes.dao.AbstractDao;
import com.epam.web.rotten.potatoes.dao.extractor.FilmFieldsExtractor;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.dao.mapper.FilmRowMapper;
import com.epam.web.rotten.potatoes.model.Film;

import java.sql.Connection;
import java.util.List;

public class FilmDaoImpl extends AbstractDao<Film> implements FilmDao {
    private static final FilmRowMapper FILM_ROW_MAPPER = new FilmRowMapper();
    private static final FilmFieldsExtractor FILM_FIELDS_EXTRACTOR = new FilmFieldsExtractor();

    private static final String GET_TOP_FILMS = "SELECT * FROM films LIMIT ? OFFSET ?";
    private static final String FILMS_TABLE = "films";
    private static final String SAVE_FILM = "INSERT INTO films(id, title, director, poster, default_rate) VALUES(?,?,?,?,?)";
    private static final String UPDATE_FILM = "UPDATE films SET title=?, director=?, poster=?, default_rate=? WHERE id=?";

    private static final String GET_FILMS_LIST_BY_DIRECTOR = "SELECT * FROM films WHERE director=?";

    public FilmDaoImpl(Connection connection) {
        super(connection, FILM_ROW_MAPPER, FILM_FIELDS_EXTRACTOR, FILMS_TABLE, SAVE_FILM, UPDATE_FILM);
    }

    @Override
    public List<Film> findFilmByDirector(String director) throws DaoException {
        return executeQuery(GET_FILMS_LIST_BY_DIRECTOR, director);
    }

    @Override
    public List<Film> findFilmsPartList(int amount, int from) throws DaoException {
        int offset = from - 1;
        return executeQuery(GET_TOP_FILMS, amount, offset);
    }
}