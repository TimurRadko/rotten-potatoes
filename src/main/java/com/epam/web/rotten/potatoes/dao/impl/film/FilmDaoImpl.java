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
    private static final String GET_FILMS_PART = "SELECT * FROM films limit ?, ?";
    private static final String GET_TOP_FILMS = "SELECT * FROM films ORDER BY ?";
    private static final String FILMS_TABLE = "films";
    private static final String INSERT_FILM = "INSERT INTO films(title, director, poster, avg_rate) VALUES(?,?,?,?)";

    private static final String GET_FILMS_LIST_BY_DIRECTOR = "SELECT * FROM films WHERE director=?";

    public FilmDaoImpl(Connection connection) {
        super(connection, FILM_ROW_MAPPER, FILM_FIELDS_EXTRACTOR, FILMS_TABLE, INSERT_FILM);
    }

    @Override
    public List<Film> sortByRow(String rowName) throws DaoException {
        return executeQuery(GET_TOP_FILMS, rowName);
    }

    @Override
    public List<Film> findFilmsPartList(int startsWith, int endsWith) throws DaoException {
        return executeQuery(GET_FILMS_PART, startsWith, endsWith);
    }
}
