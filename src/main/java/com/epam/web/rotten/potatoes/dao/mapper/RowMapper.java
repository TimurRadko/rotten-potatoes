package com.epam.web.rotten.potatoes.dao.mapper;

import com.epam.web.rotten.potatoes.model.Entity;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Serializable & Cloneable> {
    T map(ResultSet resultSet) throws SQLException;

    static RowMapper<? extends Entity> create(String table) {
        switch (table) {
            case User.TABLE:
                return new UserRowMapper();
            case Film.TABLE:
                return new FilmRowMapper();
            case UserAction.TABLE:
                return new UserActionRowMapper();
            default:
                throw new IllegalArgumentException();
        }
    }
}
