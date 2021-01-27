package com.epam.web.rotten.potatoes.dao.mapper;

import com.epam.web.rotten.potatoes.model.UserComment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCommentRowMapper implements RowMapper<UserComment> {
    private static final String ID = "id";
    private static final String COMMENT = "comment";
    private static final String FILM_ID = "film_id";
    private static final String USER_ID = "user_id";

    @Override
    public UserComment map(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(ID);
        String comment = resultSet.getString(COMMENT);
        int filmId = resultSet.getInt(FILM_ID);
        int userId = resultSet.getInt(USER_ID);
        return new UserComment(id, comment, filmId, userId);
    }
}