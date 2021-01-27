package com.epam.web.rotten.potatoes.dao.mapper;

import com.epam.web.rotten.potatoes.model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmRowMapper implements RowMapper<Film> {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DIRECTOR = "director";
    private static final String POSTER = "poster";
    private static final String DEFAULT_RATE = "default_rate";

    @Override
    public Film map(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(ID);
        String title = resultSet.getString(TITLE);
        String director = resultSet.getString(DIRECTOR);
        String poster = resultSet.getString(POSTER);
        String stringDefaultRate = resultSet.getString(DEFAULT_RATE);
        double defaultRate = Double.parseDouble(stringDefaultRate);
        return new Film(id, title, director, poster, defaultRate);
    }
}