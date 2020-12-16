package com.epam.web.rotten.potatoes.dao.impl.user;

import com.epam.web.rotten.potatoes.dao.AbstractDao;
import com.epam.web.rotten.potatoes.dao.extractor.UserFieldsExtractor;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.dao.mapper.UserRowMapper;
import com.epam.web.rotten.potatoes.model.User;

import java.sql.Connection;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();
    private static final UserFieldsExtractor USER_FIELDS_EXTRACTOR = new UserFieldsExtractor();
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = SHA1(?)";
    private static final String TABLE_NAME = "users";

    private static final String UPDATE_USER = "";

    public UserDaoImpl(Connection connection) {
        super(connection, USER_ROW_MAPPER, USER_FIELDS_EXTRACTOR, TABLE_NAME, UPDATE_USER);
    }

    @Override
    public Optional<User> findUserByLoginAndId(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }
}
