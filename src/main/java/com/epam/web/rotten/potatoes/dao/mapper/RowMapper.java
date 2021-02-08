package com.epam.web.rotten.potatoes.dao.mapper;

import com.epam.web.rotten.potatoes.model.*;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Describes a class that creates and returns an expected object based on the data in the current row of ResultSet
 *
 * @param <T> type of object to be retrieved from the database
 */
public interface RowMapper<T extends Serializable & Cloneable> {
    /**
     * @param resultSet - passed into the method resultSet
     * @return T - The one of all entities in the DB
     * @throws SQLException in case of errors
     */
    T map(ResultSet resultSet) throws SQLException;

    /**
     * @param table - passed into the method the table Name
     * @return RowMapper<? extends Entity> - returns the corresponding RowMapper
     */
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