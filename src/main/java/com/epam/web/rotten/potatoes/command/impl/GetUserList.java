package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.service.user.UserService;

import java.util.ArrayList;
import java.util.List;

public class GetUserList implements Command {
    private final UserService userService;
    private static final String USERS = "users";
    private static final String USERS_PAGE = "WEB-INF/views/users.jsp";
    private static final String USER = "user";

    public GetUserList(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        List<User> users = userService.getTopUsers();
        List<User> appropriateUsers = getAppropriateUser(users);
        requestContext.setRequestAttribute(USERS, appropriateUsers);
        return CommandResult.forward(USERS_PAGE);
    }

    private List<User> getAppropriateUser(List<User> users) {
        List<User> userList = new ArrayList<>();
        for (User user : users) {
            if (USER.equals(user.getRights())) {
                userList.add(user);
            }
        }
        return userList;
    }
}