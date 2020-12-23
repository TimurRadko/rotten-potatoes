package com.epam.web.rotten.potatoes.dao.mapper;

import com.epam.web.rotten.potatoes.model.UserAction;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserActionRowMapper implements RowMapper<UserAction> {
    private static final String ID = "id";
    private static final String FILM_RATE = "film_rate";
    private static final String REVIEW = "review";
    private static final String USER_ID = "user_id";
    private static final String FILM_ID = "film_id";

    @Override
    public UserAction map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID);
        int rate = resultSet.getInt(FILM_RATE);
        String review = resultSet.getString(REVIEW);
        int userId = resultSet.getInt(USER_ID);
        int filmId = resultSet.getInt(FILM_ID);
        return new UserAction(id, rate, review, userId, filmId);
    }
}
