package com.epam.web.rotten.potatoes.dao.mapper;

import com.epam.web.rotten.potatoes.model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmRowMapper implements RowMapper<Film> {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DIRECTOR = "director";
    private static final String POSTER = "poster";
    private static final String AVG_RATE = "avg_rate";

    @Override
    public Film map(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(ID);
        String title = resultSet.getString(TITLE);
        String director = resultSet.getString(DIRECTOR);
        String poster = resultSet.getString(POSTER);
        double avgRate = Double.parseDouble(resultSet.getString(AVG_RATE));
        return new Film(id, title, director, poster, avgRate);
    }
}
