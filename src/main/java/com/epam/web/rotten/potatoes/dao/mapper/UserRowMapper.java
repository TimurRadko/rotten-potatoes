package com.epam.web.rotten.potatoes.dao.mapper;

import com.epam.web.rotten.potatoes.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String RIGHTS = "rights";
    private static final String RATE = "rate";
    private static final String BLOCKED = "blocked";

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(ID);
        String login = resultSet.getString(LOGIN);
        String rights = resultSet.getString(RIGHTS);
        int rate = resultSet.getInt(RATE);
        boolean blocked = resultSet.getBoolean(BLOCKED);
        return new User(id, login, rights, rate, blocked);
    }
}