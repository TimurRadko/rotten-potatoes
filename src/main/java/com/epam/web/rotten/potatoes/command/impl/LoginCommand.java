package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.service.user.UserService;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.Optional;

public class LoginCommand implements Command {
    private final UserService userService;

    private static final String USER_ATTRIBUTE = "user";
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String LOGIN_PAGE = "WEB-INF/views/login.jsp";
    private static final String FILMS_PAGE_COMMAND = "/rotten-potatoes/controller?command=films";

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_LOGIN = "errorLogin";
    private static final String ERROR_USER_BLOCKED = "errorBlocked";

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String login = requestContext.getRequestParameter(LOGIN_PARAMETER);
        String password = requestContext.getRequestParameter(PASSWORD_PARAMETER);

        Optional<User> optionalUser = userService.login(login, password);

        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_LOGIN);
            return CommandResult.forward(LOGIN_PAGE);
        }
        if (user.isBlocked()) {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_USER_BLOCKED);
            return CommandResult.forward(LOGIN_PAGE);
        }
        requestContext.setSessionAttribute(USER_ATTRIBUTE, user);
        return CommandResult.redirect(FILMS_PAGE_COMMAND);
    }
}
