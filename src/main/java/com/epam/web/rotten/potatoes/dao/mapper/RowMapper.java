package com.epam.web.rotten.potatoes.dao.mapper;

import com.epam.web.rotten.potatoes.model.*;

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
            case UserComment.TABLE:
                return new UserCommentRowMapper();
            default:
                throw new IllegalArgumentException();
        }
    }
}
