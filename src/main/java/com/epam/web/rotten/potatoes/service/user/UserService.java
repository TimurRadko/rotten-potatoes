package com.epam.web.rotten.potatoes.service.user;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String login, String password) throws ServiceException;
    List<User> getAllUsers() throws ServiceException;
    Optional<User> getUserById(Integer id) throws ServiceException;
    Optional<Integer> blockUnblockUser(User user) throws ServiceException;
}
