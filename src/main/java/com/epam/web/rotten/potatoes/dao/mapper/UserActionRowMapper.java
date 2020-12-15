package com.epam.web.rotten.potatoes.dao.mapper;

import com.epam.web.rotten.potatoes.model.UserAction;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserActionRowMapper implements RowMapper<UserAction> {
    private static final String ID = "id";
    private static final String FILM_RATE = "films-rate";
    private static final String REVIEW = "review";

    @Override
    public UserAction map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID);
        int rate = resultSet.getInt(FILM_RATE);
        String review = resultSet.getString(REVIEW);

        //TODO: Correct these two statements
        int userId = resultSet.getInt("user_id");
        int filmId = resultSet.getInt("film_id");

        return new UserAction(id, rate, review, userId, filmId);
    }
}
