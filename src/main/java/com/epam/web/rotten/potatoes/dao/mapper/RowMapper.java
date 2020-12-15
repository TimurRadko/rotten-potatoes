package com.epam.web.rotten.potatoes.dao.mapper;

import com.epam.web.rotten.potatoes.model.Entity;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Entity> {
    T map(ResultSet resultSet) throws SQLException;

    static RowMapper<? extends Entity> create(String table) {
        switch (table) {
            case User.TABLE:
                return new UserRowMapper();
            case Film.TABLE:
                return new FilmRowMapper();
            default:
                throw new IllegalArgumentException();
        }
    }
}
