package com.epam.web.rotten.potatoes.dao.impl.user;

import com.epam.web.rotten.potatoes.dao.Dao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    /**
     * Finds a user by login and password.
     * Returns result as Optional of User found, or empty Optional if found nothing.
     *
     * @param login    - User's login on the web-application
     * @param password - User's login on the web-application
     * @return Optional<User> - container that is contained User
     * @throws DaoException in case of errors
     */
    Optional<User> findUserByLoginAndId(String login, String password) throws DaoException;

    /**
     * Finds a list of Users, which sorted by Rate.
     *
     * @return - List<User> - get Users List int the table 'users' and sorting their by Rate
     * @throws DaoException in case of errors
     */
    List<User> findTopUsers() throws DaoException;
}
