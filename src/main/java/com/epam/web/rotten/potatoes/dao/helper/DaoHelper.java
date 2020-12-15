package com.epam.web.rotten.potatoes.dao.helper;

import com.epam.web.rotten.potatoes.connection.ConnectionPool;
import com.epam.web.rotten.potatoes.connection.ProxyConnection;
import com.epam.web.rotten.potatoes.dao.impl.action.UserActionDao;
import com.epam.web.rotten.potatoes.dao.impl.action.UserActionDaoImpl;
import com.epam.web.rotten.potatoes.dao.impl.film.FilmDao;
import com.epam.web.rotten.potatoes.dao.impl.film.FilmDaoImpl;
import com.epam.web.rotten.potatoes.dao.impl.user.UserDao;
import com.epam.web.rotten.potatoes.dao.impl.user.UserDaoImpl;
import com.epam.web.rotten.potatoes.exceptions.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {
    private ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
        this.connection = pool.getConnection();
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public FilmDao createFilmDao() {
        return new FilmDaoImpl(connection);
    }

    public UserActionDao createUserActionDao() {
        return new UserActionDaoImpl(connection);
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() {
        connection.close();
    }
}
