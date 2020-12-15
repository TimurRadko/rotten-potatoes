package com.epam.web.rotten.potatoes.dao.mapper;

import com.epam.web.rotten.potatoes.model.Rights;
import com.epam.web.rotten.potatoes.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String RIGHTS = "rights";
    private static final String RATE = "rate";

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID);
        String login = resultSet.getString(LOGIN);
        String password = resultSet.getString(PASSWORD);

        String rightsString = resultSet.getString(RIGHTS);
        Rights rights = Rights.valueOf(rightsString.toUpperCase());

        double rate = resultSet.getDouble(RATE);
        return new User(id, login, password, rights, rate);
    }
}
