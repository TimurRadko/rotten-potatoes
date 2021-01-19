package com.epam.web.rotten.potatoes.service.user;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     *
     * @param login - User's login on the web-application
     * @param password - User's password on the web-application
     * @return Optional<User> - container that is contained User
     * @throws ServiceException
     */
    Optional<User> login(String login, String password) throws ServiceException;

    /**
     *
     * @param id - userId in the table users
     * @return Optional<User> - container that is contained User
     * @throws ServiceException
     */
    Optional<User> getUserById(Integer id) throws ServiceException;

    /**
     *
     * @param user -  passed into the method User appropriate Entity
     * @return - Optional<Integer> - container that is contained userId
     * @throws ServiceException
     */
    Optional<Integer> save(User user) throws ServiceException;

    /**
     *
     * @return List<User> - get Users List int the table 'users' and sorting their by Rate
     * @throws ServiceException
     */
    List<User> getTopUsers() throws ServiceException;
}