package com.epam.web.rotten.potatoes.dao.impl.user;

import com.epam.web.rotten.potatoes.dao.Dao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    /**
     * @param login    - User's login on the web-application
     * @param password - User's login on the web-application
     * @return Optional<User> - container that is contained User
     * @throws DaoException
     */
    Optional<User> findUserByLoginAndId(String login, String password) throws DaoException;

    /**
     * @return - List<User> - get Users List int the table 'users' and sorting their by Rate
     * @throws DaoException
     */
    List<User> getTopUsers() throws DaoException;
}
